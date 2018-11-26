using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.Text.RegularExpressions;
using TechnicalTestWeb2.Models;

namespace TechnicalTestWeb2
{
    class EstShipDateValidate : ValidationAttribute
    {
        protected override ValidationResult IsValid(object value,
                            ValidationContext validationContext)
        {
            Shipment shipment = (Shipment)validationContext.ObjectInstance;

            DateTime estShipDate = (DateTime)value;
            DateTime orderPlacedDate = (DateTime)shipment.DateOrderPlaced;

            int result = DateTime.Compare(estShipDate, orderPlacedDate);

            if (result > 0)
            {
                return new ValidationResult("Estimated ship date cannot be earlier that order date");
            }
            else if (result == 0)
            {
                return new ValidationResult("Estimated ship date cannot be the same as ship date");
            }
            
            return ValidationResult.Success;
        }
    }
}
