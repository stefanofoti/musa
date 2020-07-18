using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MuSa.Models
{
    public class Artworks
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public string ID { get; set; }			// Artwork's ID [format IDi]
        public string Name { get; set; }        // Name of the artwork
        public string Author { get; set; }      // Author of the artwork
        public string Year { get; set; }        // Year of realization
        public string Description { get; set; } // Brief description of the artwork
        public string Image { get; set; }		// Image's path
	}
}