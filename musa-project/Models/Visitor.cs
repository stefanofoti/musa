using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MuSa.Models
{
    public class Visitor
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }
        public string BeaconID { get; set; }
        public virtual ICollection<TourItem> Tours { get; set; }

        public Visitor()
        {
            Tours = new List<TourItem>();
        }

        //public void AddTourItem(TourItem tourItem){
        //    Tours.Add(tourItem);
        //}

    }
}