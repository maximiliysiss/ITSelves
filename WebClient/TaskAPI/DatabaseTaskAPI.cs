using JetBrains.Annotations;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TaskAPI.Models;

namespace TaskAPI
{
    public class DatabaseTaskAPI : DbContext
    {
        public DatabaseTaskAPI(DbContextOptions options) : base(options)
        {
        }
        public DbSet<TaskAPI.Models.Category> Category { get; set; }
        public DbSet<TaskAPI.Models.Task> Task { get; set; }
    }
}
