using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CommonActionsWeb;
using CommonLibrary.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace WebClient.Controllers
{
    public class HomeController : Controller
    {
        public Worker Worker { get; set; }

        [HttpGet]
        public IActionResult Index()
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            ViewBag.Worker = Worker = this.GetWorkerByToken(HttpContext.Session.GetString("Token"));
            HttpContext.Session.SetString("Role", Worker.Role);
            if (Worker.Role == "ROLE_OPERATOR")
                return View("Operator");
            return View("Worker");
        }
    }
}