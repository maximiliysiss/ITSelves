using CommonActions;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebClient.Models;

namespace WebClient.Controllers
{
    public class AuthController : Controller
    {

        NetConnection net = new NetConnection { BaseURL = Configuration.URLS["Auth"] };

        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(string key)
        {
            string token = (await net.LoadObjectByPost<TokenModel>("/login/", $"{{\"key\":\"{key}\"}}")).Token;
            HttpContext.Session.SetString("Token", token);
            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult SignOut()
        {
            HttpContext.Session.Remove("Token");
            return View("Index");
        }
    }
}
