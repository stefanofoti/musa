using System;
using Microsoft.Azure.EventHubs;
using System.Threading.Tasks;
using System.Threading;
using System.Text;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;

using MuSa.Models;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.DependencyInjection;
//using System.Web.Helpers;
using System.Linq;
using Newtonsoft.Json.Linq;
using Microsoft.EntityFrameworkCore;

namespace MuSa.Controllers
{
    [ApiController]
    class ReceiveController : BackgroundService
    {

        private IServiceScopeFactory _serviceScopeFactory;

        public ReceiveController(IServiceScopeFactory serviceScopeFactory)
        {
            _serviceScopeFactory = serviceScopeFactory;
        }


        // Event Hub-compatible endpoint
        // az iot hub show --query properties.eventHubEndpoints.events.endpoint --name {your IoT Hub name}
        private readonly static string s_eventHubsCompatibleEndpoint = "sb://ihsuprodamres089dednamespace.servicebus.windows.net/";

        // Event Hub-compatible name
        // az iot hub show --query properties.eventHubEndpoints.events.path --name {your IoT Hub name}
        private readonly static string s_eventHubsCompatiblePath = "iothub-ehub-rg-newhub-3130239-c9a97cad6f";
        
        // az iot hub policy show --name service --query primaryKey --hub-name {your IoT Hub name}
        private readonly static string s_iotHubSasKey = "lVrPV7LeIaU3nvz14IS87jq4liZET/YapZeO/hFN1Ho=";
        private readonly static string s_iotHubSasKeyName = "service";
        private static EventHubClient s_eventHubClient;

        // Asynchronously create a PartitionReceiver for a partition and then start 
        // reading any messages sent from the simulated client.
        private async Task ReceiveMessagesFromDeviceAsync(string partition, CancellationToken ct)
        {
            // Create the receiver using the default consumer group.
            // For the purposes of this sample, read only messages sent since 
            // the time the receiver is created. Typically, you don't want to skip any messages.
            var eventHubReceiver = s_eventHubClient.CreateReceiver("$Default", partition, EventPosition.FromEnqueuedTime(DateTime.Now));
            Console.WriteLine("Create receiver on partition: " + partition);
            while (true)
            {
                if (ct.IsCancellationRequested) break;
                Console.WriteLine("Listening for messages on: " + partition);
                // Check for EventData - this methods times out if there is nothing to retrieve.
                var events = await eventHubReceiver.ReceiveAsync(100);

                // If there is data in the batch, process it.
                if (events == null) continue;

                foreach(EventData eventData in events)
                { 
                  string data = Encoding.UTF8.GetString(eventData.Body.Array);
                  Console.WriteLine("Message received on partition {0}:", partition);
                  Console.WriteLine("  {0}:", data);

                  Console.WriteLine("Calling AddMessage method");
                    try
                    {
                        this.AddMessage(data);
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine(e.ToString());                
                    }
                  Console.WriteLine("AddMessage method END");

                  Console.WriteLine("Application properties (set by device):");
                  foreach (var prop in eventData.Properties)
                  {
                    Console.WriteLine("  {0}: {1}", prop.Key, prop.Value);
                  }
                  Console.WriteLine("System properties (set by IoT Hub):");
                  foreach (var prop in eventData.SystemProperties)
                  {
                    Console.WriteLine("  {0}: {1}", prop.Key, prop.Value);
                  }
                    //MusaContext context = DependencyResolver.Current.GetService<MusaContext>();
                    //Console.WriteLine("contextnull: " + (context==null).ToString());
                }
            }
        }

        public void AddMessage(string message)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                MusaContext context = scope.ServiceProvider.GetService<MusaContext>();

                //dynamic data = Json.Decode(message);
                dynamic data = JObject.Parse(message);
                Console.WriteLine("Message parsed with timestamp: " + data.timestamp);
                string timestamp = data.timestamp;
                foreach (dynamic user in data.users)
                {
                    Console.WriteLine("Adding message...");
                    string id = user.id;
                    Console.WriteLine("Looking for visitor with id: " + id);
                    var visitor = context.Visitors.Include(visitor => visitor.Tours)
                        .Where(v => v.BeaconID == id)
                        .FirstOrDefault();
                    Console.WriteLine("Visitor found: " + visitor);
                    if(visitor == null)
                    {
                        Console.WriteLine("Creating new visitor...");
                        visitor = new Visitor { BeaconID = id };
                        context.Visitors.Add(visitor);
                    }
                    if(user.artworks==null){
                        //return;
                    }
                    string[] artworks = user.artworks.ToObject<string[]>();                    
                    foreach(var artId in artworks)
                    {   
                        TourItem ti = visitor.Tours.LastOrDefault();
                        if(ti != null && ti.ArtworkId == artId.ToString()){
                            Console.WriteLine("+-- Updating same artwork...");
                            ti.Time++;
                        } else {
                            Console.WriteLine("+-- New artwork in user tour...");
                            ti = new TourItem {ArtworkId = artId.ToString(), Time = 1};
                            context.TourItems.Add(ti);
                            visitor.Tours.Add(ti);
                        }
                    }
                }
                context.SaveChanges();
            }

            Console.WriteLine("+-- Saved!");
        }
        public async Task<string> Init()
        {

            //MusaContext context = DependencyResolver.Current.GetService<MusaContext>();
            //Console.WriteLine("contextnull: " + (context==null).ToString());            

            var connectionString = new EventHubsConnectionStringBuilder(new Uri(s_eventHubsCompatibleEndpoint), s_eventHubsCompatiblePath, s_iotHubSasKeyName, s_iotHubSasKey);
            s_eventHubClient = EventHubClient.CreateFromConnectionString(connectionString.ToString());

            var runtimeInfo = await s_eventHubClient.GetRuntimeInformationAsync();
            var d2cPartitions = runtimeInfo.PartitionIds;

            CancellationTokenSource cts = new CancellationTokenSource();

            Console.CancelKeyPress += (s, e) =>
            {
                e.Cancel = true;
                cts.Cancel();
                Console.WriteLine("Exiting...");
            };

            var tasks = new List<Task>();
            foreach (string partition in d2cPartitions)
            {
                tasks.Add(ReceiveMessagesFromDeviceAsync(partition, cts.Token));
            }

            //Task.WaitAll(tasks.ToArray());
            return "";
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            return Init();
        }

        public override Task StartAsync(CancellationToken cancellationToken)
        {
            //await Init();
            return base.StartAsync(cancellationToken);
        }

        public override Task StopAsync(CancellationToken cancellationToken)
        {
            return base.StopAsync(cancellationToken);
        }
    }
}