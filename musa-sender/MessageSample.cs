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
        private string _dataBuffer;

        public void setText(string dataBuffer){
            _dataBuffer = dataBuffer;
        }

        public MessageSample(DeviceClient deviceClient)
        {
            _deviceClient = deviceClient ?? throw new ArgumentNullException(nameof(deviceClient));
            s_randomGenerator = new Random();
        }

        public async Task RunSampleAsync(){
            await SendEvent().ConfigureAwait(false);
        }

        private async Task SendEvent(){
            Message eventMessage = new Message(Encoding.UTF8.GetBytes(_dataBuffer));
            Console.WriteLine("Sending message " + _dataBuffer);
            await _deviceClient.SendEventAsync(eventMessage).ConfigureAwait(false);
        }
    }
}