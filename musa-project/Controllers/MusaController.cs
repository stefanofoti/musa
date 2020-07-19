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
