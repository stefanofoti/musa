using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MuSa.Models
{
    public class Tour
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public string ID { get; set; }			  // Tour's ID [format IDa, IDb...]
        public string Name { get; set; }          // Tour's Name
        public string TourArtworks { get; set; }  // Artworks in the planned tour   
    }
}