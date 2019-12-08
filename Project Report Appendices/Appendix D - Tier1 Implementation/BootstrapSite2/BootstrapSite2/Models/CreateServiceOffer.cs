using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace BootstrapSite2.Models
{
    public class CreateServiceOffer
    {
       
        

        [Display(Name = "Service Title")]
        public String ServiceTitle { get; set; }


        [Display(Name = "Service Category")]
        public String ServiceCategory { get; set; }

        [Display(Name = "Service Description")]
        public String ServiceDecs { get; set; }
        [Display(Name = "CPR number")]
        public String CPR { get; set; }

        [Display(Name = "Address")]
        public String Address { get; set; }
        [Display(Name = "BookingTime")]
        public String BookingTime { get; set; }
        [Display(Name = "Salary")]
        public String Salary { get; set; }

    }
    
}