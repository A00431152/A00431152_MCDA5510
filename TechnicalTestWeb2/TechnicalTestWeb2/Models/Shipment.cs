namespace TechnicalTestWeb2.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Shipment")]
    public partial class Shipment
    {
        public int ID { get; set; }

        [Display(Name = "Courier Name")]
        [StringLength(50)]
        public string Courier { get; set; }

        [Display(Name = "Tracking Number")]
        [StringLength(50)]
        public string TrackingNumber { get; set; }

        public int? CustomerID { get; set; }

        [Display(Name = "Shipment Status")]
        [StringLength(50)]
        public string Status { get; set; }

        [Display(Name = "Service Type")]
        [StringLength(50)]
        public string ServiceType { get; set; }

        [Display(Name = "Est. Ship Date")]
        [EstShipDateValidate]
        [Column(TypeName = "date")]
        public DateTime? EstShippedDate { get; set; }

        [Display(Name = "Date Order Placed")]
        [Column(TypeName = "date")]
        public DateTime? DateOrderPlaced { get; set; }

        public virtual Customer Customer { get; set; }
    }
}
