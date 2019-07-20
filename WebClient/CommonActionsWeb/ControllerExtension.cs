using CommonActions;
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
        public static JObject GetUserByToken(this ControllerBase controllerBase)
        {
            var token = controllerBase.Request.Headers["Tokens"].ToString();
            return NetConnection.LoadJObject($"{Configuration.URLS["Auth"]}/auth",
                $"{{\"token\":\"{token}\"}}", HttpMethod.Post,
                new Dictionary<string, string>()).GetAwaiter().GetResult();
        }
    }
}
