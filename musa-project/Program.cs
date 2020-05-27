using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;
using System.Threading;
using System;
using MuSa.Controllers;
using MuSa.Models;
using MuSa;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace MuSa
{
    public class Program
    {

        public static void Main(string[] args)
        {
            
            //new Thread(() => 
            //{
                //Thread.CurrentThread.IsBackground = true; 
            //).Start();

            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseUrls("https://*:5000", "http://*:5002");
                    webBuilder.UseStartup<Startup>();
                });
    }
}
