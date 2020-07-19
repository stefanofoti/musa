import context  # Ensures paho is in PYTHONPATH
import paho.mqtt.publish as publish

print('Sending...');
publish.single("musa/aw1", 'G_ON', hostname="test.mosquitto.org");
print('Sent.');