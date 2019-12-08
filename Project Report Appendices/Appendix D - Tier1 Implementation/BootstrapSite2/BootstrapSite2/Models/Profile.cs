using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace BootstrapSite2.Models
{
    public class Profile
    {
        [Display(Name = "First name")]
        public String FName { get; set; }

        [Display(Name = "Last name")]
        public String LName { get; set; }
        [Display(Name = "CPR number")]
        public String CPR { get; set; }

        [Display(Name = "Phone number")]
        public String Phone { get; set; }

        [Display(Name = "Description")]
        public String Description { get; set; }

        [Display(Name = "County")]
        public String County { get; set; }
        [Display(Name = "Skills")]
        public String Skills { get; set; }
        [Display(Name = "Current Password")]
        public String CPassword { get; set; }
        [Display(Name = "New Password")]
        public String NPassword { get; set; }
        [Display(Name = "Repeat Password")]
        public String RPassword { get; set; }

    }
}