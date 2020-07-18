using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using MuSa.Models;
using System;
using System.IO;
using System.Text;

using System.Text.Json;
using System.Text.Json.Serialization;
using JsonSerializer = System.Text.Json.JsonSerializer;
using System.Dynamic;

namespace MuSa.Controllers
{
    [Route("api/[controller]")] 
    [ApiController]
    public class TourController : Controller
    {
        private readonly MusaContext _context;

        public TourController(MusaContext context)
        {
            _context = context;
            if(_context.Tour.Count() == 0){
                Console.WriteLine("+-- initializing tours...");
                
                Tour t1 = new Tour{ ID = "IDa", Name = "The Welcome Tour", TourArtworks = "Discobolus (ID1), Venus de Milo (ID3), Laocoon Group (ID4), Artemision Bronze (ID8)" };
                Tour t2 = new Tour{ ID = "IDb", Name = "The MuSa Tour", TourArtworks = "Nike of Samothrace (ID2), Apollo Belvedere (ID5), Peplos Kore (ID6), Farnese Hercules (ID7)" };
                
                context.Tour.Add(t1);
                context.Tour.Add(t2);
                
                context.SaveChanges();
            }
        }

        // GET: api/Tour/ - List of the Tours
        // GET: api/Tour/IDx/(method) - Any info you need

        [HttpGet]
        public string GetTourList()
        {
            string jsonString;
            jsonString = JsonSerializer.Serialize(_context.Tour);
            return jsonString;
        }   

        [HttpGet("{ID}")]
        public string GetInfo(string ID) // To get every info about a Tour through the ID
        {
            string jsonString;
            int idn = ID[2] - 97;
            jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn]);
            return jsonString;
        }

        [HttpGet("{ID}/Name")]
        public string GetName(string ID) // To get the Tour's Name through the ID
        {
            string jsonString;
            int idn = ID[2] - 97;
            jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn].Name);
            return jsonString;
        }

        [HttpGet("{ID}/Artworks")]
        public string GetTourArtworks(string ID) // To get the Tour's artworks through the ID
        {
            string jsonString;
            int idn = ID[2] - 97;
            jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn].TourArtworks);
            return jsonString;
        }

        [HttpPost("GetTour")]
        public async Task<string> ReadStringDataManual() // To get the Tour after the profiling survey
        {
            using (StreamReader reader = new StreamReader(Request.Body, Encoding.UTF8))
            {   
                string jsonString;
                string result = await reader.ReadLineAsync();
                result = await reader.ReadLineAsync();
                string[] words = result.Split('"');
                //Console.WriteLine(words[3]);
                int age = Int16.Parse(words[3]);
                if (age<29)
                {
                    jsonString = JsonSerializer.Serialize("IDa");
                    return jsonString;
                } else {
                    jsonString = JsonSerializer.Serialize("IDb");
                    return jsonString;
                }    
            }
        }
    
    }
}