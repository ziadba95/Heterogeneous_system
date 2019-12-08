using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace BootstrapSite2.Models
{
    public class LogIn
    {
        [Display(Name = "CPR number")]
        public String CPR { get; set; }

        [Display(Name = "Password")]
        public String Password { get; set; }
    }
}