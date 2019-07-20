using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TaskAPI;
using TaskAPI.Models;

namespace TaskAPI.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class TasksController : ControllerBase
    {
        private readonly DatabaseTaskAPI _context;

        public TasksController(DatabaseTaskAPI context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Models.Task>>> GetTask()
        {
            return await _context.Task.ToListAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Models.Task>> GetTask(int id)
        {
            var task = await _context.Task.FindAsync(id);

            if (task == null)
            {
                return NotFound();
            }

            return task;
        }

        [HttpPost]
        [Route("edit")]
        public async Task<IActionResult> PutTask(Models.Task task)
        {
            _context.Entry(task).State = EntityState.Modified;

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

        [HttpPost]
        [Route("add")]
        public async Task<ActionResult<Models.Task>> PostTask(Models.Task task)
        {
            _context.Task.Add(task);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTask", new { id = task.ID }, task);
        }

        [HttpDelete("{id}")]
        [Route("delete")]
        public async Task<ActionResult<Models.Task>> DeleteTask(int id)
        {
            var task = await _context.Task.FindAsync(id);
            if (task == null)
            {
                return NotFound();
            }

            _context.Task.Remove(task);
            await _context.SaveChangesAsync();

            return task;
        }
    }
}
