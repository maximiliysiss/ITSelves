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
        public async static Task<JObject> LoadJObject(string url, string content, HttpMethod httpMethod, Dictionary<string, string> headers)
        {
            using (var client = new HttpClient())
            {
                HttpRequestMessage httpRequestMessage = new HttpRequestMessage();
                httpRequestMessage.Method = httpMethod;
                foreach (var pair in headers)
                    httpRequestMessage.Headers.Add(pair.Key, pair.Value);
                httpRequestMessage.Content = new StringContent(content);
                httpRequestMessage.RequestUri = new Uri(url);
                var result = await client.SendAsync(httpRequestMessage);
                if (result.IsSuccessStatusCode)
                    return JObject.Parse(new StreamReader(await result.Content.ReadAsStreamAsync()).ReadToEnd());
            }
            return null;
        }
    }
}
