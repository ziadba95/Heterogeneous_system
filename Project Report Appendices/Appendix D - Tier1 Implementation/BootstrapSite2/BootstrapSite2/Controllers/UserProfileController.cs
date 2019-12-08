using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using BootstrapSite2.Models;
namespace BootstrapSite2.Controllers
{
    public class UserProfileController : Controller
    {
        private Tier2.WebserviceClient ws = new Tier2.WebserviceClient();
        private List<Tier2.serviceOffer> offers;
        private List<Tier2.serviceRequest> requests;
        private List<Tier2.applicant> applicants;
   

        // GET: MyPosts
        public ActionResult MyPostOffers()
        { 
            
            try
            {
                offers = ws.getMyServiceOffers(long.Parse(Session["user"].ToString())).ToList();
                return View(offers);
            }catch(System.ServiceModel.FaultException e)
            {
                ViewBag.errorMessage=e.Message;
                return View("Error");
            }

        }
        // GET: MyPosts
        public ActionResult MyPostRequests()
        {
         
            try
            {
                requests = ws.getMyServiceRequests(long.Parse(Session["user"].ToString())).ToList();
                return View(requests);
            }
            catch (System.ServiceModel.FaultException e)
            {
                ViewBag.errorMessage = e.Message;
                return View("Error");
            }
        }


        public ActionResult Index()
        {
            if (Session["user"] == null) return View();
            else return Index();
        }
        public ActionResult EditOffers(int id)
        {
            offers = ws.getMyServiceOffers(long.Parse(Session["user"].ToString())).ToList();
            foreach (var item in offers)
            {
                if (item.id == id)
                {
                    return View(item);

                }
            }
            return View();
        }

        public ActionResult EditRequests(int Id)
        {
            requests = ws.getMyServiceRequests(long.Parse(Session["user"].ToString())).ToList();
            foreach (var item in requests)
            {
                if (item.id == Id)
                {
                    return View(item);

                }
            }
            return View();
        }
        //public ActionResult SubmitEditRequest(Tier2.user user)
        //{

           

        //    return RedirectToAction("ProfileDetails2", "UserProfile");
        //}
        public ActionResult SubmitEditOffer(Tier2.user user)
        {

            ws.editProfile(Session["user"].ToString(), user.description, user.city, user.phone, user.email, user.password, user.vPassword);

            return RedirectToAction("ProfileDetails2", "UserProfile");
        }
        public ActionResult ProfileDetails2()
        {
            ViewBag.Counties = ws.getCounties();
            return View(ws.getProfile(Session["user"].ToString()));


        }

        public ActionResult SubmitNewInfo(Tier2.user user)
        {

            ws.editProfile(Session["user"].ToString(), user.description, user.city, user.phone, user.email, user.password, user.vPassword);

            return RedirectToAction("ProfileDetails2", "UserProfile");
        }

        public ActionResult LogOut(LogIn form)
        {
            if (Session["user"] != null)

                Session["user"] = null;
            return RedirectToAction("Index", "Home");





        }
        public ActionResult DeleteOffer(int Id)
        {
           
                offers = ws.getMyServiceOffers(long.Parse(Session["user"].ToString())).ToList();
                foreach (var item in offers)
                {
                    if (item.id == Id)
                    {
                        ws.deleteOffer(Id);
                    }
                }
            
           return RedirectToAction("MyPostOffers","UserProfile");
        }
    
    
    public ActionResult DeleteRequest(int Id)
    {
        requests = ws.getMyServiceRequests(long.Parse(Session["user"].ToString())).ToList();
        foreach (var item in requests)
        {
            if (item.id == Id)
            {
                ws.deleteRequest(Id);
            }
        }
        return RedirectToAction("MyPostRequests", "UserProfile");
    }
        public ActionResult Applications()
        {
            try
            {
                return View(ws.getOffersIAppliedFor(long.Parse(Session["user"].ToString())).ToList());
            }
            catch (System.ServiceModel.FaultException e)
            {
                ViewBag.ErrorMessage = e.Message;
                return View("error");
            }
            }
        public ActionResult DeleteApplication(int Id)
        {
            offers = ws.getServiceOffersNF(long.Parse(Session["user"].ToString())).ToList();

            foreach (var item in offers)
            {
                if (item.id == Id)
                {
                    ws.deleteApplicant(long.Parse(Session["user"].ToString()), Id);
                }

            }


            return RedirectToAction("Applications", "UserProfile");
        }
        public ActionResult DeleteApplicant(int cpr)
        {
            applicants = ws.getApplicants(cpr).ToList();

            foreach (var item in applicants)
            {
                if (item.cpr == cpr)
                {
                    ws.deleteApplicant(long.Parse(Session["user"].ToString()), cpr);
                }

            }


            return RedirectToAction("Applicants", "UserProfile");
        }
        public ActionResult Applicants(int Id)
        {
            try
            {
                offers = ws.getMyServiceOffers(long.Parse(Session["user"].ToString())).ToList();
                foreach (var item in offers)
                {
                    if (item.id == Id)
                    {
                        try
                        {
                            applicants = ws.getApplicants(item.id).ToList();
                            return View(applicants);
                        } catch (System.ServiceModel.FaultException e)
                        {
                            ViewBag.ErrorMessage = e.Message;
                            return View("Error");
                    
                        }
                    }
                    
                }
                return View("Error");
            }catch(System.ServiceModel.FaultException e)
            {
                ViewBag.errorMessage = e.Message;
                return View("Error");
            }
 
         
        }


       
        public ActionResult EmployAndPay()
        {
            return Redirect("https://www.paypal.com/us/home");
        }
       
    }
}