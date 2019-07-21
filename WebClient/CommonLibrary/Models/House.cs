using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary.Models
{
    public class House
    {
        public int ID { get; set; }
        public string Street { get; set; }
        public string HouseNumber { get; set; }
        public string City { get; set; }
        public string Area { get; set; }
        public string Index { get; set; }

        public override string ToString()
        {
            return $"{Area}, {Street}, {HouseNumber}";
        }
    }
}
