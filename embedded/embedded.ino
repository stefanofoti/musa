#include "EspMQTTClient.h"
#include <BLEAdvertisedDevice.h>
#include <BLEDevice.h>
#include <BLEAddress.h>
#include <BLEScan.h>
#include <ArduinoJson.h>
#include <sstream>
#define MQTT_MAX_PACKET_SIZE 2048

EspMQTTClient client(
  "ap",//Vodafone-A80016520
  "1234567890aa",//w6xplmysfem6t2w8
  "test.mosquitto.org",
  "clientnamex",
  1883
);

const int PIN = 2;
const int CUTOFF = -60;
const int OUTPUT_SIZE = 10000;
const int MESSAGE_SIZE = 5500;
const int TUPLE_SIZE = 59;

void setup() {
  Serial.begin(115200);
  Serial.println("Initializing bluetooth...");
  pinMode(PIN, OUTPUT);
  BLEDevice::init("");
  Serial.println("Bluetooth initialized");
  while(!client.isWifiConnected()){
      Serial.println("Connecting to WiFi...");
      client.loop();
      delay(2000);
  }
  while(!client.isMqttConnected()){
      Serial.println("Connecting to broker...");
      delay(1000);
  }
}


void onConnectionEstablished() {
  Serial.println("Connection enstablished...");
  client.subscribe("mytopic/test", [] (const String &payload)  {
    Serial.println("Got new message!");
    Serial.println(payload);
  });
  client.publish("musa/aw1", "This is a message");
}


void loop() {
  client.loop();

  while(!client.isWifiConnected()){
      Serial.println("CONNECTION LOST! Connecting to WiFi...");
      client.loop();
      delay(2000);
  }
  while(!client.isMqttConnected()){
      Serial.println("CONNECTION LOST! Connecting to broker...");
      delay(1000);
  }
  //To get b2d messages
  //StaticJsonDocument<OUTPUT_SIZE> doc;
  DynamicJsonDocument  doc(OUTPUT_SIZE);
  //DynamicJsonDocument  doc(200);
  int addedTuples=0;
  for(int itr = 0; itr < 10; itr++){
    if(addedTuples*TUPLE_SIZE > MESSAGE_SIZE/2){
      Serial.print("Many users in the museum. Anticipating the buffer flush and message sending at itr. "); 
      Serial.println(itr); 
      itr=10;
      continue;
    }
    BLEScan *scan = BLEDevice::getScan();
    scan->setActiveScan(true);
    BLEScanResults results = scan->start(1);   
    JsonObject object = doc.createNestedObject();
    object["iteration"] = itr;
    for (int i = 0; i < results.getCount(); i++) {
      BLEAdvertisedDevice device = results.getDevice(i);
      //Serial.println(device.toString().c_str());       
      std::stringstream ss;
      if (device.haveManufacturerData()) {
           for (uint8_t i = 0; i < device.getManufacturerData().length(); i++) {
               //ss << ' ' << std::setfill('0') << std::setw(2) << std::hex << (int)((uint8_t *) device.getManufacturerData().data())[i];
               ss << std::hex << (int)((uint8_t *) device.getManufacturerData().data())[i];
           }
           object[ss.str()] = device.getRSSI();
           addedTuples++;
      }
    }
  }
  char output[MESSAGE_SIZE];
  size_t sz = serializeJson(doc, output);  
  Serial.println("Sending JSON"); 
  client.publish("musa/ID2", output);
  Serial.println(output);
  //digitalWrite(PIN, best > CUTOFF ? HIGH : LOW);
  //client.publish("mytopic/test", "This is a message");
  
}
