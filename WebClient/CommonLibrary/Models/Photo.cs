using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace CommonLibrary.Models
{
    public class Photo
    {
        [Key]
        public int ID { get; set; }
        public byte[] Image { get; set; }
    }
}
