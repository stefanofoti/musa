import context  # Ensures paho is in PYTHONPATH
import paho.mqtt.publish as publish

print('Sending...');
publish.single("musa/ID2/led", 'G_OFF', hostname="test.mosquitto.org");
print('Sent.');