import context  # Ensures paho is in PYTHONPATH
import paho.mqtt.client as mqtt
import paho.mqtt.publish as publish
import json
import statistics #for Kalman
import threading
import time
from azure.iot.device import IoTHubDeviceClient, Message

def on_message(mqttc, obj, msg):
	print("Got from the board: ", msg.topic, msg.payload)
	parseMsg(msg);


def on_subscribe(mqttc, obj, mid, granted_qos):
    print("Subscribed: " + str(mid) + " " + str(granted_qos))


def parseMsg(msg):
	aw = getAw(msg.topic);
	updateTimeStamps(artworksTimeStamps, aw);
	data = json.loads(msg.payload);
	dataMap = initRowDataMap(data);
	applyKalman(dataMap, aw);
	trJson = json.dumps(trackingData);
	print('Parsed. Result: \n',trJson);

def initRowDataMap(data):
	#print("init row data");
	dataMap = {};
	for itr in data:
		if 'iteration' in itr: 
			del itr['iteration'];
		for k in itr.keys():
			beaconID = getId(k);
			if beaconID not in dataMap: 
				dataMap[beaconID] = [];
			l = dataMap[beaconID];
			l.append(itr[k]);
	return dataMap;

def getId(k):
	if len(k)>36:
		index = k.find("beac");
		return k[index+4:index+36];
	return k;

def getAw(aw):
	return aw[5:];

def applyKalman(dataMap, aw):
	print("Applying kalman filter... ");
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
	#print("computing kalman on: ", len(inputValues));
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
	currentLedON="";
	ts=0;
	client = IoTHubDeviceClient.create_from_connection_string(azureConnectionString);
	while True:
		print('Sending message to Azure IoT HUB...');
		output = getClosest(ts);
		outputJson = json.dumps(output);
		sendToAzure(client, outputJson)
		print(output);
		manageLeds(currentLedON);
		ts=ts+1;
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
	for k in usersList:
		if "artworks" in k:
			aws = k["artworks"]
			if len(aws)>0:
				#print("k[artworks] ", k["artworks"]);
				aw = aws[0];
		if aw not in stats:
			stats[aw] = 0;
		stats[aw]=stats[aw]+1;
	output["users"] = usersList;
	print("Current statistics: ", stats);
	return output;

def	manageLeds(currentLedON):
	max = 0;
	aw = "";
	for k in stats.keys():
		if stats[k]>max:
			max = stats[k];
			aw = k;
	if len(currentLedON)>0 and currentLedON!=aw:
		#turn off the led to currentLedON;
		print ( "Turning off led for", currentLedON);
		publish.single("musa/"+currentLedON+"/led", 'G_OFF', hostname="test.mosquitto.org");
	if len(aw)>0:
		#turn on the led to to  
		print ( "Turning on led for", aw);
		publish.single("musa/"+aw+"/led", 'G_ON', hostname="test.mosquitto.org");
		currentLedON = aw;

def sendToAzure(client, message):
    try:
        print( "Sending message: {}".format(message))
        message = message.encode('utf8')
        client.send_message(message)        
        print ( "Message successfully sent" )        
    except Exception:
        print ( "Error sending to azure" );



trackingData = {}
artworksTimeStamps = {};
farRSSI = -100;
defaultVariance = 2;
stats = {};
azureConnectionString = "HostName=rg-newhub.azure-devices.net;DeviceId=musa-device;SharedAccessKey=hzgs10oJcjWY3vT1LkLvPhh3EHpYu/xPMv8oe47RhUQ=";


mqttc = mqtt.Client();
mqttc.on_subscribe = on_subscribe
mqttc.connect("test.mosquitto.org", 1883, 60)
mqttc.subscribe("musa/ID1", 0);
mqttc.subscribe("musa/ID2", 0);
mqttc.subscribe("musa/ID3", 0);
mqttc.subscribe("musa/ID4", 0);
mqttc.subscribe("musa/ID5", 0);
mqttc.subscribe("musa/ID6", 0);
mqttc.subscribe("musa/ID7", 0);
mqttc.subscribe("musa/ID8", 0);
mqttc.on_message = on_message;
threading.Timer(10.0, startSending).start()
mqttc.loop_forever();
