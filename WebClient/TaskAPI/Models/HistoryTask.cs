using CommonActions;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace TaskAPI.Models
{
    public class HistoryTask
    {
        public HistoryTask()
        {
            //WorkerLoad = new DynamicLoadBy(this, "Worker", Configuration.URLS["Task"]);
            HouseLoad = new DynamicLoadBy(this, "House", $"{Configuration.URLS["HCS"]}/Houses");
        }
        public int ID { get; set; }
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
        public DynamicLoadBy HouseLoad { get; set; }
        /// <summary>
        /// Работник
        /// </summary>
        public int Worker { get; set; }
        [NotMapped]
        public DynamicLoadBy WorkerLoad { get; set; }
        virtual public Task OriginalTask { get; set; }
    }
}