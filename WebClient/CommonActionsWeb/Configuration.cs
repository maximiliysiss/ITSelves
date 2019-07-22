using System;
using System.Collections.Generic;
using System.Text;

namespace CommonActions
{
    public static class Configuration
    {
        public static Dictionary<string, string> URLS { get; set; } = new Dictionary<string, string>()
        { {"Task", "http://85.143.11.233:8000" },{ "HCS", "http://85.143.11.233:8001"},
            { "Auth", "http://85.143.10.92:8001"}, { "Schedule", "http://85.143.11.233:8002"} };
    }
}
