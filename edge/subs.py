
import context  # Ensures paho is in PYTHONPATH
import paho.mqtt.client as mqtt
import json
import statistics #for Kalman
import threading
import time
from azure.iot.device import IoTHubDeviceClient, Message

trackingData = {}
artworksTimeStamps = {};
farRSSI = -100;
defaultVariance = 2;

azureConnectionString = "HostName=rg-newhub.azure-devices.net;DeviceId=musa-device;SharedAccessKey=hzgs10oJcjWY3vT1LkLvPhh3EHpYu/xPMv8oe47RhUQ=";

def on_message(mqttc, obj, msg):
	print("ricevuto: ", msg.payload)
	parseMsg(msg);


def on_subscribe(mqttc, obj, mid, granted_qos):
    print("Subscribed: " + str(mid) + " " + str(granted_qos))


def parseMsg(msg):
	aw = msg.topic;
	updateTimeStamps(artworksTimeStamps, aw);
	data = json.loads(msg.payload);
	dataMap = initRowDataMap(data);
	applyKalman(dataMap, aw);
	trJson = json.dumps(trackingData);
	print('-------------------------\n',trJson);

def initRowDataMap(data):
	dataMap = {};
	for itr in data:
		if 'iteration' in itr: 
			del itr['iteration'];
		for k in itr.keys():
			if k not in dataMap: 
				dataMap[k] = [];
			l = dataMap[k];
			l.append(itr[k]);
	print("+-- dataMap: ", dataMap);
	return dataMap;

def applyKalman(dataMap, aw):
	print("applying kalman");
	for k in dataMap.keys():
		if k not in trackingData:
			trackingData[k] = {};
		currentUserTracking = trackingData[k];
		if aw not in currentUserTracking:
			currentUserTracking[aw] = {};
		userAwData = currentUserTracking[aw];
		if "variance" in userAwData:
			variance = userAwData["variance"];
		else:
			variance = defaultVariance;
		rssiValues = dataMap[k];
		[val, newVar] = kalmanCalc(rssiValues, variance, 4);
		userAwData["variance"] = newVar;
		userAwData["rssi"] = val;
		userAwData["timestamp"] = artworksTimeStamps[aw];


def kalmanCalc(inputValues, initialVariance, noise):
	print("computing kalman on: ", len(inputValues));
	if len(inputValues)==1:
		return [inputValues[0], 0];
	variance = initialVariance;
	processNoise = noise;
	measurementNoise = statistics.variance(inputValues);
	mean = statistics.mean(inputValues);
	for value in inputValues:
		variance = variance + processNoise;
		kalmanGain = variance/(variance + measurementNoise);
		mean = mean + kalmanGain * (value - mean);
		variance = variance - (kalmanGain * variance);
	return [mean, variance];

def updateTimeStamps(dictionary, aw):
	if aw not in dictionary: 
		dictionary[aw]=0;
	else:
		dictionary[aw]=dictionary[aw]+1;

def startSending():
	ts=0;
	client = IoTHubDeviceClient.create_from_connection_string(azureConnectionString);
	while True:
		print('sending...');
		output = getClosest(ts);
		outputJson = json.dumps(output);
		sendToAzure(client, outputJson)
		print(output);
		ts=ts+1;
		#TO DO send to Azure
		time.sleep(5);

def getClosest(ts):
	closest = {};
	output = {};
	output["timestamp"] = ts;
	usersList = [];
	for k in trackingData.keys():
		artworksForUser = trackingData[k];
		closest["rssi"] = farRSSI;
		currnentUser = {};
		for aw in artworksForUser.keys():
			current = artworksForUser[aw];
			if current["timestamp"] < artworksTimeStamps[aw]:
				current["rssi"] = farRSSI;
				#del artworksForUser[aw];
			if current["rssi"] > closest["rssi"]:
				closest["rssi"] = current["rssi"];
				currnentUser["id"] = k;
				currnentUser["rssi"] = closest["rssi"];
				currnentUser["artworks"] = [aw];
				#output[k] = aw;
		if currnentUser:
			usersList.append(currnentUser);
	output["users"] = usersList;
	return output;


def sendToAzure(client, message):
    try:
        print( "Sending message: {}".format(message))
        message = message.encode('utf8')
        client.send_message(message)        
        print ( "Message successfully sent" )        
    except Exception:
        print ( "Error sending to azure" );


lock=False;
mqttc = mqtt.Client();
mqttc.on_message = on_message
mqttc.on_subscribe = on_subscribe
mqttc.connect("test.mosquitto.org", 1883, 60)
mqttc.subscribe("musa/aw1", 0);
mqttc.subscribe("musa/aw2", 0);
threading.Timer(5.0, startSending).start()
mqttc.loop_forever();
print('-------------------------\n',trJson);
