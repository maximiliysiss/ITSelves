using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary.Models
{
    public class User
    {
        public int ID { get; set; }
        public string Role { get; set; }
        public string Name { get; set; }
    }

    public class Worker : User
    {
        public Category Category { get; set; }
    }
}