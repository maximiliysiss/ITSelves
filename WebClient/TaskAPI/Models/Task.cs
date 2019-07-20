using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace TaskAPI.Models
{
    public enum TaskStatus
    {
        Create,
        InProgress,
        InConsideration,
        Confirmed,
        Declined
    }


    public class Task
    {
        public int ID { get; set; }
        /// <summary>
        /// Исторические изменения
        /// </summary>
        public List<HistoryTask> HistoryTasks { get; set; }
        /// <summary>
        /// Время выполнения 
        /// </summary>
        public DateTime DateTime { get; set; } = DateTime.Now;
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
        public List<Photo> Photos { get; set; }
        /// <summary>
        /// Дом
        /// </summary>
        public string House { get; set; }
        [NotMapped]
        public JObject HouseJSON
        {
            get => JObject.Parse(House);
            set => House = value.ToString();
        }
        /// <summary>
        /// Работник
        /// </summary>
        public string Worker { get; set; }
        [NotMapped]
        public JObject WorkerJSON
        {
            get => JObject.Parse(House);
            set => House = value.ToString();
        }
    }
}
