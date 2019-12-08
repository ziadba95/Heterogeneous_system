using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;
namespace BootstrapSite2.Models
{
    public class SignUp
    {
        [Display(Name = "First name")]
        public String FName { get; set; }

        [Display(Name = "Last name")]
        public String LName { get; set; }

        [Display(Name = "Phone number")]
        public String Phone { get; set; }

        [Display(Name = "Email")]
        public String Email { get; set; }

        [Display(Name = "CPR number")]
        public String CPR { get; set; }

        [Display(Name = "County")]
        public String County { get; set; }

        [Display(Name = "Password")]
        public String Password { get; set; }

        //[Compare("Password")]
        [Display(Name = "Verify Password")]
        public String vPassword { get; set; }


        [Display(Name = "Address")]
        public String Address { get; set; }
    }

}
