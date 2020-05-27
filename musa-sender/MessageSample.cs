// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

using System;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using Microsoft.Azure.Devices.Client;

namespace sender_cs
{
    public class MessageSample
    {
        private static Random s_randomGenerator;
        private DeviceClient _deviceClient;

        public MessageSample(DeviceClient deviceClient)
        {
            _deviceClient = deviceClient ?? throw new ArgumentNullException(nameof(deviceClient));
            s_randomGenerator = new Random();
        }

        public async Task RunSampleAsync(){
            await SendEvent().ConfigureAwait(false);
        }

        private async Task SendEvent(){
            string dataBuffer;
            dataBuffer = "{\"timestamp\":\"2020-2xxx\",\"users\":[{\"mac\":\"A1:B2:C3:D4:E5:F6\",\"artworks\":[\"1\",\"1\",\"1\",\"2\",\"2\"]},{\"mac\":\"A1:B2:C3:D4:E5:F7\",\"artworks\":[\"2\",\"2\",\"2\",\"2\",\"2\"]}]}";
            Message eventMessage = new Message(Encoding.UTF8.GetBytes(dataBuffer));
            Console.WriteLine("Sending message " + dataBuffer);
            await _deviceClient.SendEventAsync(eventMessage).ConfigureAwait(false);
        }
    }
}