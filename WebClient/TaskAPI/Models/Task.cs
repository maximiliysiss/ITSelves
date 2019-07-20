using CommonActions;
using Newtonsoft.Json;
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
        public Task()
        {
            //WorkerLoad = new DynamicLoadBy(this, "Worker", Configuration.URLS["Task"]);
            HouseLoad = new DynamicLoadBy(this, "House", $"{Configuration.URLS["HCS"]}/Houses");
        }
        public int ID { get; set; }
        /// <summary>
        /// Исторические изменения
        /// </summary>
        virtual public List<HistoryTask> HistoryTasks { get; set; }
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
        virtual public Category Category { get; set; }
        /// <summary>
        /// Фотографии
        /// </summary>
        virtual public List<Photo> Photos { get; set; }
        /// <summary>
        /// Дом
        /// </summary>
        public int House { get; set; }
        [NotMapped]
        [JsonIgnore]
        private DynamicLoadBy HouseLoad;
        public JObject HouseJSON => JObject.Parse(HouseLoad.ToString());
        /// <summary>
        /// Работник
        /// </summary>
        public int Worker { get; set; }
        [NotMapped]
        [JsonIgnore]
        private DynamicLoadBy WorkerLoad;
        //public string WorkerJSON => HouseLoad.ToString();
    }
}
