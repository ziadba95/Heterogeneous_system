using System.ServiceModel;
using System.Web.Mvc;

using BootstrapSite2.Models;
namespace BootstrapSite2.Controllers
{
    
    
    public class PortfolioController : Controller
    {

        private Tier2.WebserviceClient ws = new Tier2.WebserviceClient();
        public ActionResult Index()
        {
            if (Session["user"] == null) return View();
            else return Index();
        }

        public ActionResult SignUp()
        {
            ViewBag.Counties =  ws.getCounties();
            return View("SignUp");
        }

        public ActionResult LogIn()
        {
            return View("LogIn");
        }
        public ActionResult SubmitSignUp(Tier2.user user)
        {ViewBag.Counties = ws.getCounties();
            //fName, lName, password, phone, cPR, city, email
            try
            {
                ws.signUp(user.fName, user.lName, user.password, user.vPassword,user.phone, user.cPR, user.city, user.email);
                Session["user"] = user.cPR;
                Session["name"] = ws.logIn(user.cPR, user.password);
                
                return RedirectToAction("Index", "Home");
            }
            //System.ServiceModel.FaultException`1
            catch (System.ServiceModel.FaultException e)
            {
                ViewBag.errorMessage = e.Message;
                return View("SignUp");
            }
            
        }
       
        public ActionResult SubmitLogIn(LogIn form)
        {
            try
            {
                ws.logIn(form.CPR, form.Password);
               Session["name"] = ws.logIn(form.CPR, form.Password);
                Session["user"] = form.CPR;
                return RedirectToAction("Index", "Home");
               
            }
            catch (FaultException e)
            {
                ViewBag.errorMessage = e.Message;
                return View("LogIn");
            }
        }

       

    }
}
