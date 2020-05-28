using Microsoft.EntityFrameworkCore;
using System;

namespace MuSa.Models
{
    public class MusaContext : DbContext
    {
        public MusaContext (DbContextOptions<MusaContext> options) : base(options)
        {
            Console.WriteLine("Called MusaContext constructor");
        }

        public DbSet<Visitor> Visitors { get; set; }
        public DbSet<TourItem> TourItems { get; set; }
    }
}
