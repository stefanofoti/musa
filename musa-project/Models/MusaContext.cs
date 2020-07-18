using Microsoft.EntityFrameworkCore;
using System;

namespace MuSa.Models
{
    public class MusaContext : DbContext
    {
        public MusaContext (DbContextOptions<MusaContext> options) : base(options)
        {
        }

        public DbSet<Visitor> Visitors { get; set; }
        public DbSet<TourItem> TourItems { get; set; }
        public DbSet<Artworks> Artworks { get; set; }
        public DbSet<Tour> Tour { get; set; }
    }
}
