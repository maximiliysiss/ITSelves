using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary.Models
{
    public class Company
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public virtual House House { get; set; }
        public string Phone { get; set; }
    }
}
