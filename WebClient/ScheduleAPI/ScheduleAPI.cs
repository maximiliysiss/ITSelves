using CommonLibrary.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;

namespace ScheduleAPI
{
    public class DatabaseScheduleAPI : DbContext
    {
        public DatabaseScheduleAPI(DbContextOptions options) : base(options)
        {
        }
        public DbSet<Schedule> Schedule { get; set; }
    }
}
