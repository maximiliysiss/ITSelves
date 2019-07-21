using CommonActions;
using CommonLibrary.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace CommonActionsWeb
{
    public static class ControllerExtension
    {
        public static User GetUserByToken(this ControllerBase controllerBase, string token)
        {
            return new NetConnection { BaseURL = Configuration.URLS["Auth"] }.LoadObjectByPost<User>("/auth/",
                $"{{\"token\":\"{token}\"}}").GetAwaiter().GetResult();
        }

        public static Worker GetWorkerByToken(this ControllerBase controllerBase, string token)
        {
            return new NetConnection { BaseURL = Configuration.URLS["Auth"] }.LoadObjectByPost<Worker>("/auth/",
                $"{{\"token\":\"{token}\"}}").GetAwaiter().GetResult();
        }

        public static bool IsAuth(this ControllerBase controllerBase)
        {
            string token;
            if (controllerBase.Request.Headers.ContainsKey("Token"))
                token = controllerBase.Request.Headers["Token"];
            else
                token = controllerBase.HttpContext.Session.GetString("Token");
            var json = controllerBase.GetUserByToken(token);
            if (json == null)
                return false;
            return true;
        }
    }
}
