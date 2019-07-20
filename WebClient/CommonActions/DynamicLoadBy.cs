using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Reflection;
using System.Text;

namespace CommonActions
{
    public class DynamicLoadBy
    {
        [JsonIgnore]
        private JObject data;
        [JsonIgnore]
        public JObject Data
        {
            set
            {
                data = value;
                NetConnection.LoadJObject(Url, JsonConvert.SerializeObject(data), HttpMethod.Post, NetConnection.Tokens).GetAwaiter().GetResult();
            }
            get => data = NetConnection.LoadJObject($"{Url}/{PropertyInfo.GetValue(Object)}", "", HttpMethod.Get, NetConnection.Tokens).GetAwaiter().GetResult();
        }
        [JsonIgnore]
        public object Object { get; set; }
        [JsonIgnore]
        public PropertyInfo PropertyInfo { get; set; }
        [JsonIgnore]
        public string Url { get; set; }
        public DynamicLoadBy(object obj, string name, string url)
        {
            Object = obj;
            PropertyInfo = obj.GetType().GetProperty(name);
            Url = url;
        }

        public override string ToString()
        {
            return Data.ToString().Replace("\r\n", string.Empty);
        }
    }
}
