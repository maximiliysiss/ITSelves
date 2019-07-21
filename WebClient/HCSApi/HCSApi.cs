using CommonLibrary.Models;
using JetBrains.Annotations;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary.Databases
{
    public class DatabaseHCSApi : DbContext
    {
        public DatabaseHCSApi(DbContextOptions options) : base(options)
        {
        }

        public DbSet<House> House { get; set; }
    }
}
