using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace HCSApi.Models
{
    public class House
    {
        public int ID { get; set; }
        public string Street { get; set; }
        public string HouseNumber { get; set; }
        public string City { get; set; }
        public string Area { get; set; }
        public string Index { get; set; }
    }
}
