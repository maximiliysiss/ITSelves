using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace CommonLibrary.Models
{
    public class Task
    {
        public int ID { get; set; }
        /// <summary>
        /// Время выполнения 
        /// </summary>
        public DateTime DateTime { get; set; } = DateTime.Now;
        public int Creator { get; set; }
        public string Name { get; set; }
        [NotMapped]
        public string ShortName => Description?.Length > 45 ? Description.Substring(0, 45) : Description ?? string.Empty;
        /// <summary>
        ///  Статус
        /// </summary>
        public TaskStatus? TaskStatus { get; set; }
        /// <summary>
        /// Описание
        /// </summary>
        public string Description { get; set; }
        /// <summary>
        /// Категория
        /// </summary>
        public Category Category { get; set; }
        /// <summary>
        /// Фотографии
        /// </summary>
        virtual public List<Photo> Photos { get; set; }
        virtual public List<Photo> PhotosResult { get; set; }
        /// <summary>
        /// Дом
        /// </summary>
        public int House { get; set; }
        /// <summary>
        /// Работник
        /// </summary>
        public int Worker { get; set; }
        public Rate Rate { get; set; }
        public string Comment { get; set; }
        public DateTime TimeWork { get; set; }
    }
}
