using CommonLibrary.Models;
using JetBrains.Annotations;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;

namespace TaskAPI
{
    public class DatabaseTaskAPI : DbContext
    {
        public DatabaseTaskAPI(DbContextOptions options) : base(options)
        {
        }

        public DbSet<CommonLibrary.Models.Task> Task { get; set; }
        public DbSet<Photo> Photos { get; set; }
    }
}
