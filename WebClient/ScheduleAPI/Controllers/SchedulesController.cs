using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using CommonActions;
using CommonActionsWeb;
using CommonLibrary.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json.Linq;
using ScheduleAPI;

namespace ScheduleAPI.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class SchedulesController : ControllerBase
    {
        private readonly DatabaseScheduleAPI _context;

        public SchedulesController(DatabaseScheduleAPI context)
        {
            _context = context;
        }

        // GET: api/Schedules
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Schedule>>> GetSchedule()
        {
            if (!this.IsAuth())
                return NotFound();
            return await _context.Schedule.ToListAsync();
        }

        // GET: api/Schedules/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Schedule>> GetSchedule(int id)
        {
            if (!this.IsAuth())
                return NotFound();
            var schedule = await _context.Schedule.FindAsync(id);

            if (schedule == null)
            {
                return NotFound();
            }

            return schedule;
        }

        [HttpGet("{id}")]
        [Route("getWorkerSchedule")]
        public async Task<ActionResult<FullSchedule>> GetWorkerSchedule(int id)
        {
            if (!this.IsAuth())
                return NotFound();

            var actualTasks = await new NetConnection { BaseURL = Configuration.URLS["Task"] }.LoadObjectByPost<List<CommonLibrary.Models.Task>>("/getWorkerTask/{id}");
            var groupTasks = actualTasks.GroupBy(x => x.House).OrderByDescending(x => x.Count())
                .SelectMany(x => x.ToList()).ToList();
            var todayTasks = groupTasks.Where(x => x.DateTime == DateTime.Today);
            foreach (var t in todayTasks)
                groupTasks.Remove(t);
            return new FullSchedule
            {
                Schedules = _context.Schedule.Where(x => x.Worker == id).ToList(),
                Tasks = todayTasks.Concat(groupTasks).ToList()
            };
        }

        // PUT: api/Schedules/5
        [HttpPut]
        [Route("edit")]
        public async Task<IActionResult> Edit(Schedule schedule)
        {
            if (!this.IsAuth())
                return NotFound();
            _context.Entry(schedule).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                return NotFound();
            }

            return NoContent();
        }

        // POST: api/Schedules
        [HttpPost]
        [Route("add")]
        public async Task<ActionResult<Schedule>> Add(Schedule schedule)
        {
            if (!this.IsAuth())
                return NotFound();
            _context.Schedule.Add(schedule);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetSchedule", new { id = schedule.ID }, schedule);
        }

        // DELETE: api/Schedules/5
        [HttpDelete("{id}")]
        [Route("delete")]
        public async Task<ActionResult<Schedule>> DeleteSchedule(int id)
        {
            if (!this.IsAuth())
                return NotFound();
            var schedule = await _context.Schedule.FindAsync(id);
            if (schedule == null)
            {
                return NotFound();
            }

            _context.Schedule.Remove(schedule);
            await _context.SaveChangesAsync();

            return schedule;
        }
    }
}
