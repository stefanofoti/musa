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

        public static void Main(string[] args)
        {
            deviceClient = DeviceClient.CreateFromConnectionString(deviceConnectionString, transportType);
            var msg = new MessageSample(deviceClient);
            while(true){
                msg.RunSampleAsync().GetAwaiter().GetResult();
                Thread.Sleep(5000);
            }
        }
    }
}