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

using System.Text.Json;
using System.Text.Json.Serialization;
using JsonSerializer = System.Text.Json.JsonSerializer;
using System.Dynamic;

namespace MuSa.Controllers
{
    [Route("api/[controller]")] 
    [ApiController]
    public class MusaController : Controller
    {
        private readonly MusaContext _context;

        public MusaController(MusaContext context)
        {
            _context = context;
            if(_context.Visitors.Count() == 0){
                Console.WriteLine("+-- adding a visitor...");
                Console.WriteLine("+-- adding...");
                Visitor v1 = new Visitor { BeaconID = "A" };
                TourItem item1a = new TourItem{ArtworkId = "ID1", Time = 3};
                TourItem item2a = new TourItem{ArtworkId = "ID2", Time = 6};
                TourItem item3a = new TourItem{ArtworkId = "ID3", Time = 2};
                v1.Tours=new List<TourItem>();
                v1.Tours.Add(item1a);
                v1.Tours.Add(item2a);
                v1.Tours.Add(item3a);
                context.Visitors.Add(v1);
                context.TourItems.Add(item1a);
                context.TourItems.Add(item2a);
                context.TourItems.Add(item3a);
                context.SaveChanges();
            }

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

        // GET: api/Musa
        [HttpGet]
        public string GetStatus()
        {
            //return await _context.Visitors.ToListAsync();
            //string jsonString;
            //jsonString = JsonSerializer.Serialize(_context.Visitors.ToList());
            //jsonString = JsonSerializer.Serialize(_context.Visitors.ToList()[0].Tours);
            int s = _context.Visitors.Count();
            return s.ToString();
        }

        [HttpGet("list")]
        public string GetList()
        {
            //return await _context.Visitors.ToListAsync();
            string jsonString;
            jsonString = JsonSerializer.Serialize(_context.Visitors.Include(visitor => visitor.Tours).ToList());
            //jsonString = JsonSerializer.Serialize(_context.Visitors.ToList()[0].Tours);
            return jsonString;
        }           
        
        
        [HttpGet("tourlist")]
        public string GetTourList()
        {
            //return await _context.Visitors.ToListAsync();
            string jsonString;
            jsonString = JsonSerializer.Serialize(_context.TourItems.ToList());
            //jsonString = JsonSerializer.Serialize(_context.Visitors.ToList()[0].Tours);
            return jsonString;
        }        

        [HttpGet("{beaconID}")]
        public string GetLastPosition(string beaconID)
        {
            var visitor = _context.Visitors.Include(visitor => visitor.Tours)
                        .Where(v => v.BeaconID == beaconID)
                        .FirstOrDefault();
            dynamic obj = new ExpandoObject();
            if(visitor!=null){
                obj.code = 200;
                obj.ArtworkId = visitor.Tours.LastOrDefault().ArtworkId;

            } else {
                obj.code = 404;
            }
            string jsonString = JsonSerializer.Serialize(obj);
            return jsonString;
        }     
        
        [HttpGet("{beaconID}/details")]
        public string GetLastPositionDetailed(string beaconID)
        {
            var visitor = _context.Visitors.Include(visitor => visitor.Tours)
                        .Where(v => v.BeaconID == beaconID)
                        .FirstOrDefault();
            dynamic obj = new ExpandoObject();
            if(visitor!=null){
                obj.code = 200;
                string id = visitor.Tours.LastOrDefault().ArtworkId;
                obj.ArtworkId = id;
                var artwork = _context.Artworks
                    .Where(aw => aw.ID == id)
                    .FirstOrDefault<Artworks>();
                if(artwork!=null){
                    obj.Name=artwork.Name;
                    obj.Author=artwork.Author;
                    obj.Year=artwork.Year;
                    obj.Description=artwork.Description;
                    obj.Image=artwork.Image;
                }
            } else {
                obj.code = 404;
            }
            string jsonString = JsonSerializer.Serialize(obj);
            return jsonString;
        }  

    }
}
