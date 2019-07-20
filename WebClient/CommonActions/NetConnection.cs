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
        public static Dictionary<string, string> Tokens { get; private set; } = new Dictionary<string, string>();
        public async static Task<JObject> LoadJObject(string url, string content, HttpMethod httpMethod, Dictionary<string, string> headers)
        {
            using (var client = new HttpClient())
            {
                HttpRequestMessage httpRequestMessage = new HttpRequestMessage();
                httpRequestMessage.Method = httpMethod;
                foreach (var pair in headers)
                    httpRequestMessage.Headers.Add(pair.Key, pair.Value);
                if (httpMethod != HttpMethod.Get)
                    httpRequestMessage.Content = new StringContent(content);
                if (httpMethod == HttpMethod.Get)
                    httpRequestMessage.RequestUri = new Uri(url + (content.Length > 0 ? "?" : "") + content);
                else
                    httpRequestMessage.RequestUri = new Uri(url);
                var result = await client.SendAsync(httpRequestMessage);
                if (result.IsSuccessStatusCode)
                    return JObject.Parse(new StreamReader(await result.Content.ReadAsStreamAsync()).ReadToEnd());
            }
            return null;
        }
    }
}
