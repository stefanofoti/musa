using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
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
                
                Tour t1 = new Tour{ ID = "IDa", Name = "The Welcome Tour", TourArtworks = "Discobolus$Venus de Milo$Laocoon Group$Artemision Bronze" };
                Tour t2 = new Tour{ ID = "IDb", Name = "The MuSa Tour", TourArtworks = "Nike of Samothrace$Apollo Belvedere$Peplos Kore$Farnese Hercules" };
                if(_context.Artworks.Count() == 0){
                    Console.WriteLine("+-- initializing artworks...");
                    Artworks a1 = new Artworks{ ID = "ID1", Name = "Discobolus", Author = "Myron", Year = "455 BC", Description = "The Discobolus is a Greek sculpture completed at the start of the Classical Period, figuring a youthful ancient Greek athlete throwing discus, about 460/450 BC. The original Greek bronze is lost but the work is known through numerous Roman copies, both full-scale ones in marble, which was cheaper than bronze", Image = "/images/discobolus.png" };
                    Artworks a2 = new Artworks{ ID = "ID2", Name = "Nike of Samothrace", Author = "Pitocrite", Year = "200/190 BC", Description = "The statue is 244 centimetres high. It was created not only to honour the goddess, Nike, but probably also to commemorate a naval action. It conveys a sense of action and triumph as well as portraying artful flowing drapery, as though the goddess were descending to alight upon the prow of a ship", Image = "/images/nike.png" };
                    Artworks a3 = new Artworks{ ID = "ID3", Name = "Venus de Milo", Author = "Alexandros of Antioch", Year = "130/100 BC", Description = "The arms of the Venus de Milo are missing, for unknown reasons. There is a filled hole below her right breast that originally contained a metal tenon that would have supported the separately carved right arm.", Image = "/images/venus.png"  };
                    Artworks a4 = new Artworks{ ID = "ID4", Name = "Laocoon Group", Author = "Agesander and Athenodoros", Year = "100 BC/100 AD", Description = "It has been one of the most famous ancient sculptures ever since it was excavated in Rome in 1506 and placed on public display in the Vatican, where it remains. It is very likely the same statue praised in the highest terms by the main Roman writer on art, Pliny the Elder.", Image = "/images/laocoon.png" };
                    Artworks a5 = new Artworks{ ID = "ID5", Name = "Apollo Belvedere", Author = "Leochares", Year = "120/140 AD", Description = "The Apollo is now thought to be an original Roman re-creation of Hadrianic date. The distinctively Roman foot-wear is one reason scholars believe it is not a copy of an original Greek statue.", Image = "/images/apollo.png" };
                    Artworks a6 = new Artworks{ ID = "ID6", Name = "Peplos Kore", Author = "Unknown", Year = "540/530 BC", Description = "It is a statue of a girl and one of the most well-known examples of Archaic Greek art. The 118 cm-high white marble statue was originally was colourfully painted.", Image = "/images/peplos.png" };
                    Artworks a7 = new Artworks{ ID = "ID7", Name = "Farnese Hercules", Author = "Glykon", Year = "216 AD", Description = "It  is an ancient statue of Hercules, probably an enlarged copy made in the early third century AD and signed by Glykon, who is otherwise unknown; the name is Greek but he may have worked in Rome.", Image = "/images/farnese.png" };
                    Artworks a8 = new Artworks{ ID = "ID8", Name = "Artemision Bronze", Author = "Kalamis (probably)", Year = "480/470 BC", Description = "It is an ancient Greek sculpture that was recovered from the sea off Cape Artemision, in northern Euboea. It represents either Zeus or Poseidon, is slightly over lifesize at 209 cm, and would have held either a thunderbolt, if Zeus, or a trident if Poseidon.", Image = "/images/artemision.png" };
                    context.Artworks.Add(a1);
                    context.Artworks.Add(a2);
                    context.Artworks.Add(a3);
                    context.Artworks.Add(a4);
                    context.Artworks.Add(a5);
                    context.Artworks.Add(a6);
                    context.Artworks.Add(a7);
                    context.Artworks.Add(a8);
                    t1.ToursArtworksDetail.Add(a1);
                    t1.ToursArtworksDetail.Add(a3);
                    t1.ToursArtworksDetail.Add(a4);
                    t1.ToursArtworksDetail.Add(a8);
                    t2.ToursArtworksDetail.Add(a2);
                    t2.ToursArtworksDetail.Add(a5);
                    t2.ToursArtworksDetail.Add(a6);
                    t2.ToursArtworksDetail.Add(a7);
                }
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

        [HttpGet("{ID}/full")]
        public string GetDetailedInfo(string ID) // To get every info about a Tour through the ID
        {
            var tour = _context.Tour.Include(tour => tour.ToursArtworksDetail)
                .Where(t => t.ID == ID)
                .FirstOrDefault<Tour>();
            string jsonString;
            //int idn = ID[2] - 97;
            //jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn]);
            jsonString = JsonSerializer.Serialize(tour);
            return jsonString;
        }   

        [HttpGet("{ID}")]
        public string GetInfo(string ID) // To get every info about a Tour through the ID
        {
            var tour = _context.Tour
                .Where(t => t.ID == ID)
                .FirstOrDefault<Tour>();
            string jsonString;
            //int idn = ID[2] - 97;
            //jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn]);
            jsonString = JsonSerializer.Serialize(tour);
            return jsonString;
        }

        [HttpGet("{ID}/Name")]
        public string GetName(string ID) // To get the Tour's Name through the ID
        {
            string jsonString;
            var tour = _context.Tour
                .Where(t => t.ID == ID)
                .FirstOrDefault<Tour>();
            jsonString = JsonSerializer.Serialize(tour.Name);
            //int idn = ID[2] - 97;
            //jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn].Name);
            return jsonString;
        }

        [HttpGet("{ID}/Artworks")]
        public string GetTourArtworks(string ID) // To get the Tour's artworks through the ID
        {
            string jsonString;
            var tour = _context.Tour
                .Where(t => t.ID == ID)
                .FirstOrDefault<Tour>();
            //int idn = ID[2] - 97;
            //jsonString = JsonSerializer.Serialize(_context.Tour.ToList()[idn].TourArtworks);
            jsonString = JsonSerializer.Serialize(tour.TourArtworks);
            return jsonString;
        }

        [HttpPost("GetTour")]
        public async Task<string> ReadStringDataManual() // To get the Tour after the profiling survey
        {
            //var serializer = new JsonSerializer();
            //var obj = await serializer.DeserializeAsync(rawMessage);
            //dynamic obj = JsonSerializer.DeserializeAsync(Request.Body, null);
            //Console.WriteLine("+-- obj.ageValue "+obj.ageValue);
            String jsonString;
            dynamic response;
            dynamic d;
            using (StreamReader reader = new StreamReader(Request.Body, Encoding.UTF8))
            {   
                Console.WriteLine("+-- reading... ");
                var bodyText = await reader.ReadToEndAsync();
                String bodyString = bodyText.ToString();
                d = JObject.Parse(bodyString);
                int age = d.ageValue;
                Console.WriteLine("age: " + d.ageValue);
                response = new ExpandoObject();
                if(age<=29){
                    response.TourID = "IDa";
                    jsonString = JsonSerializer.Serialize(response);
                } else {
                    response.TourID = "IDb";
                    jsonString = JsonSerializer.Serialize(response);                    
                }
                Survey s = new Survey{BeaconID = d.userID, Age = d.ageValue, Gender = d.genderValue, Movie = d.movieValue, ArtStyle = d.artStyleValue, Time = d.timeValue, UseMusa = d.useMusa, CollectData = d.collectData};
                _context.Surveys.Add(s);                
                await _context.SaveChangesAsync();
                /*
                string result = await reader.ReadLineAsync();
                result = await reader.ReadLineAsync();
                string[] ageSurvey = result.Split('"');
                //Console.WriteLine(ageSurvey[3]);
                int age = Int16.Parse(ageSurvey[3]);
                if (age<29)
                {
                    jsonString = JsonSerializer.Serialize("IDa");
                    return jsonString;
                } else {
                    jsonString = JsonSerializer.Serialize("IDb");
                    return jsonString;
                }
                */
            }
            return jsonString;

        }
    
    }
}