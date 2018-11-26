namespace TechnicalTestWeb2.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class DataModel : DbContext
    {
        public DataModel()
            : base("name=DataModel")
        {
        }

        public virtual DbSet<Customer> Customers { get; set; }
        public virtual DbSet<Shipment> Shipments { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Customer>()
                .Property(e => e.firstName)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.lastName)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.addrses1)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.city)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.province)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.country)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.postal)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.phone)
                .IsUnicode(false);

            modelBuilder.Entity<Customer>()
                .Property(e => e.email)
                .IsUnicode(false);

            modelBuilder.Entity<Shipment>()
                .Property(e => e.Courier)
                .IsUnicode(false);

            modelBuilder.Entity<Shipment>()
                .Property(e => e.TrackingNumber)
                .IsUnicode(false);

            modelBuilder.Entity<Shipment>()
                .Property(e => e.Status)
                .IsUnicode(false);

            modelBuilder.Entity<Shipment>()
                .Property(e => e.ServiceType)
                .IsUnicode(false);
        }
    }
}
