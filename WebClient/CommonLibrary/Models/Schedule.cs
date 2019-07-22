using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary.Models
{
    public class FullSchedule
    {
        public List<Schedule> Schedules { get; set; }
        public List<Task> Tasks { get; set; }
    }


    public class Schedule
    {
        public Schedule()
        {
        }
        public int ID { get; set; }
        public int Worker { get; set; }
        public DayOfWeek DayOfWeek { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
    }
}

