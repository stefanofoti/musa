using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MuSa.Models
{
    public class Survey
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }
        public string BeaconID { get; set; }
        public string Age  { get; set; }
        public string Gender  { get; set; }
        public string Movie  { get; set; }
        public string ArtStyle  { get; set; }
        public string Time  { get; set; }
        public string UseMusa  { get; set; }
        public string CollectData  { get; set; }
        
    }
}