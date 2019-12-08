using System.Collections.Generic;
using System.ServiceModel;
using System.Web.Mvc;
using BootstrapSite2.Models;

namespace BootstrapSite2.Controllers
{
    
    
    public class CreatePostController : Controller
    {
        private Tier2.WebserviceClient ws = new Tier2.WebserviceClient();
        public ActionResult Index()
        {
            if (Session["user"] == null) return View();
            else return Index();
        }
        public ActionResult CreateServiceOffer()
        {
            ViewBag.Categories = ws.getCategories();
            return View("CreateServiceOffer");
        }
        public ActionResult CreateServiceRequest()
        {
            ViewBag.Categories = ws.getCategories();
            return View("CreateServiceRequest");
        }


        public ActionResult SubmitServiceOffer(CreateServiceOffer offer)
        {
            ViewBag.Categories = ws.getCategories();
            List<CreateServiceOffer> offers = new List<CreateServiceOffer>();
            try
            {
                ws.postOffer(offer.ServiceTitle, offer.ServiceCategory, offer.ServiceDecs, Session["user"].ToString(), offer.Address,offer.BookingTime,offer.Salary);
         
                return RedirectToAction("MyPostOffers", "UserProfile");
            }
            catch (FaultException e)
            {
                ViewBag.errorMessage = e.Message;
                return View("CreateServiceOffer");

            }
        }
        public ActionResult SubmitServiceRequest(CreateServiceRequest request)
        {
            ViewBag.Categories = ws.getCategories();
            List<CreateServiceRequest> requests = new List<CreateServiceRequest>();
            try
            {
                ws.postRequest(request.ServiceTitle, request.ServiceCategory, request.ServiceDecs, Session["user"].ToString());
               
                return RedirectToAction("MyPostRequests", "UserProfile");
            }
            catch (FaultException e)
            {
                ViewBag.errorMessage = e.Message;
                return View("CreateServiceRequest");

            }
        }

      
    }
}
