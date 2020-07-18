
import context  # Ensures paho is in PYTHONPATH
import paho.mqtt.client as mqtt
import json
import statistics #for Kalman
import threading
import time

trackingData = {}
ts = 0;
artworksTimeStamps = {};
farRSSI = -100;
defaultVariance = 2;

def on_message(mqttc, obj, msg):
	print("ricevuto: ", msg.payload)
	parseMsg(msg);


def on_subscribe(mqttc, obj, mid, granted_qos):
    print("Subscribed: " + str(mid) + " " + str(granted_qos))


def parseMsg(msg):
	aw = msg.topic;
	updateTimeStamps(aw);
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
		userAwData["ts"] = artworksTimeStamps[aw];


def kalmanCalc(inputValues, initialVariance, noise):
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

def updateTimeStamps(aw):
	if aw not in artworksTimeStamps: 
		artworksTimeStamps[aw]=0;
	else:
		artworksTimeStamps[aw]=artworksTimeStamps[aw]+1;

def startSending():
	while True:
		print('sending...');
		output = getClosest();
		print(output);
		#TO DO send to Azure
		time.sleep(5);

def getClosest():
	closest = {};
	output = {};
	for k in trackingData.keys():
		artworksForUser = trackingData[k];
		closest["rssi"] = farRSSI;
		for aw in artworksForUser.keys():
			current = artworksForUser[aw];
			if current['ts'] < artworksTimeStamps[aw]:
				current['rssi'] = farRSSI;
				#del artworksForUser[aw];
			if current["rssi"] > closest["rssi"]:
				closest["rssi"] = current["rssi"];
				output[k] = aw;
	return output;

lock=False;
mqttc = mqtt.Client()
mqttc.on_message = on_message
mqttc.on_subscribe = on_subscribe
mqttc.connect("test.mosquitto.org", 1883, 60)
mqttc.subscribe("musa/aw1", 0);
mqttc.subscribe("musa/aw2", 0);
threading.Timer(5.0, startSending).start()
mqttc.loop_forever();
print('-------------------------\n',trJson);
