using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace BootstrapSite2.Models
{
    public class CreateServiceRequest
    {
      

        [Display(Name = "Service Title")]
        public String ServiceTitle { get; set; }

     
        [Display(Name = "Service Category")]
        public String ServiceCategory { get; set; }

        [Display(Name = "Service Description")]
        public String ServiceDecs { get; set; }

     
    }
}