using JetBrains.Annotations;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using HCSApi.Models;

namespace HCSApi
{
    public class DatabaseHCSAPI : DbContext
    {
        public DatabaseHCSAPI(DbContextOptions options) : base(options)
        {
        }
        public DbSet<HCSApi.Models.House> House { get; set; }
    }
}
