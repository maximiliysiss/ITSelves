using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net.Http;
using System.Threading.Tasks;

namespace CommonActions
{
    public class NetConnection
    {
        public string BaseURL { get; set; }
        public async Task<T> LoadObjectByGet<T>(string url, string content = "", string token = "")
            where T : class
        {
            using (var client = new HttpClient())
            {
                HttpRequestMessage httpRequestMessage = new HttpRequestMessage
                {
                    Method = HttpMethod.Get,
                    RequestUri = new Uri(BaseURL + url + (content.Length > 0 ? "?" : "") + content)
                };
                if (token.Length > 0)
                    httpRequestMessage.Headers.Add("Token", token);
                var result = await client.SendAsync(httpRequestMessage);
                if (result.IsSuccessStatusCode)
                    return JsonConvert.DeserializeObject<T>(new StreamReader(await result.Content.ReadAsStreamAsync()).ReadToEnd());
            }
            return null;
        }

        public async Task<T> LoadObjectByDelete<T>(string url)
            where T : class
        {
            using (var client = new HttpClient())
            {
                HttpRequestMessage httpRequestMessage = new HttpRequestMessage
                {
                    Method = HttpMethod.Delete,
                    RequestUri = new Uri(BaseURL + url)
                };
                var result = await client.SendAsync(httpRequestMessage);
                if (result.IsSuccessStatusCode)
                    return JsonConvert.DeserializeObject<T>(new StreamReader(await result.Content.ReadAsStreamAsync()).ReadToEnd());
            }
            return null;
        }

        public async Task<T> LoadObjectByPost<T>(string url, string content = "", string token = "")
            where T : class
        {
            using (var client = new HttpClient())
            {
                HttpRequestMessage httpRequestMessage = new HttpRequestMessage
                {
                    Method = HttpMethod.Post,
                    RequestUri = new Uri(BaseURL + url),
                    Content = new StringContent(content)
                };
                if (token.Length > 0)
                    httpRequestMessage.Headers.Add("Token", token);
                var result = await client.SendAsync(httpRequestMessage);
                if (result.IsSuccessStatusCode)
                    return JsonConvert.DeserializeObject<T>(new StreamReader(await result.Content.ReadAsStreamAsync()).ReadToEnd());
            }
            return null;
        }
    }
}
