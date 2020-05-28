using System;
using Microsoft.Azure.Devices.Client;
using Newtonsoft.Json;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace sender_cs
{
    class Program
    {
        private static DeviceClient deviceClient;

        private static string deviceConnectionString = "";

        private static TransportType transportType = TransportType.Mqtt;

        private static string jsonMessage1 = "{\"timestamp\":\"2020-2xxx\",\"users\":[{\"mac\":\"A\",\"artworks\":[\"ID3\",\"ID3\",\"ID3\",\"ID3\",\"ID3\"]},{\"mac\":\"B\",\"artworks\":[\"2\",\"2\",\"2\",\"2\",\"2\"]}]}";
        private static string jsonMessage2 = "{\"timestamp\":\"2020-2xxx\",\"users\":[{\"mac\":\"A\",\"artworks\":[\"ID3\",\"ID4\",\"ID4\",\"ID4\",\"ID4\"]},{\"mac\":\"B\",\"artworks\":[\"2\",\"2\",\"2\",\"2\",\"2\"]}]}";
        private static string jsonMessage3 = "{\"timestamp\":\"2020-2xxx\",\"users\":[{\"mac\":\"A\",\"artworks\":[\"ID4\",\"ID4\",\"ID4\",\"ID5\",\"ID5\"]},{\"mac\":\"B\",\"artworks\":[\"2\",\"2\",\"2\",\"2\",\"2\"]}]}";
        private static string messages[] = {jsonMessage1, jsonMessage2, jsonMessage3};

        public static void Main(string[] args)
        {
            deviceClient = DeviceClient.CreateFromConnectionString(deviceConnectionString, transportType);
            var msg = new MessageSample(deviceClient);
            while(true){
                foreach (string value in messages)
                {
                    msg.setText(value)
                    msg.RunSampleAsync().GetAwaiter().GetResult();
                    Thread.Sleep(5000);                    
                }
            }
        }
    }
}