using System.Web.Mvc;
using BootstrapSite2.Models;
using System.Linq;
using System.Collections.Generic;


namespace BootstrapSite2.Controllers
{
   
    public class ServicesController : Controller
    {
        private Tier2.WebserviceClient ws = new Tier2.WebserviceClient();
        private List<Tier2.serviceOffer> offers;
        private List<Tier2.serviceRequest> requests;
    
      
        public ActionResult Index()
        {
           
          return View();
           
        }

        
        public ActionResult ListOffers()
        {
            try
            {
                offers = ws.getServiceOffersNF(long.Parse(Session["user"].ToString())).ToList();
                return View(offers);
            }
            catch(System.ServiceModel.FaultException e)
            {
                ViewBag.ErrorMessage = e.Message;
                return View("Error");
            }
        }



        public ActionResult ListRequests()
        {
            requests = new List<Tier2.serviceRequest>();
            try
            {
                requests = ws.getServiceRequestsNF(long.Parse(Session["user"].ToString())).ToList();
                return View(requests);
            }
            catch(System.ServiceModel.FaultException e)
            {
                ViewBag.ErrorMessage = e.Message;
                return View("Error");
            }
          

        }
        public ActionResult Apply(int Id)
        {
            offers = ws.getServiceOffersNF(long.Parse(Session["user"].ToString())).ToList();

            foreach (var item in offers)
            {
                if (item.id == Id)
                {
                    Session["id"] = item.id;
                    return View(item);

                }
            }
            return View();
        }
        public ActionResult SubmitApply()
        {
         

            ws.addApplicant(long.Parse(Session["user"].ToString()), int.Parse(Session["id"].ToString()));
            
            return RedirectToAction("Applications", "UserProfile");
        }
        
       
    }

   
}
