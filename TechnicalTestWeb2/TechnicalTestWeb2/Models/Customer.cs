namespace TechnicalTestWeb2.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;
    using System.Web.Mvc;

    [Table("Customer")]
    public partial class Customer
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Customer()
        {
            this.province = "Ontario"; // Default
            Shipments = new HashSet<Shipment>();
        }

        public int ID { get; set; }

        [Display(Name = "First Name")]
        [StringLength(64)]
        public string firstName { get; set; }

        [Display(Name = "Last Name")]
        [StringLength(64)]
        public string lastName { get; set; }

        [Display(Name = "Address 1")]
        [StringLength(35)]
        public string addrses1 { get; set; }

        [Display(Name = "City")]
        [StringLength(35)]
        public string city { get; set; }

        [Display(Name = "Province/State")]
        [StringLength(35)]
        public string province { get; set; }

        [Display(Name = "Country")]
        [StringLength(35)]
        public string country { get; set; }

        [Display(Name = "Postal/Zip")]
        [PostalValidate]
        public string postal { get; set; }

        [Required(ErrorMessage = "Your must provide a PhoneNumber")]
        [Display(Name = "Home Phone")]
        [DataType(DataType.PhoneNumber)]
        [RegularExpression(@"^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$", ErrorMessage = "Not a valid Phone number")]
        public string phone { get; set; }

        [Display(Name = "Email address")]
        [Required(ErrorMessage = "The email address is required")]
        [EmailAddress(ErrorMessage = "Invalid Email Address")]
        [RegularExpression("^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", ErrorMessage = "E-mail is not valid")]
        public string email { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Shipment> Shipments { get; set; }

        // This property will hold all available states for selection
        public IEnumerable<SelectListItem> provinces { get; set; }

    }
}
