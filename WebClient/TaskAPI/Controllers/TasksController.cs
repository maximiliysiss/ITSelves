using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CommonActionsWeb;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

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
        public async Task<ActionResult<IEnumerable<CommonLibrary.Models.Task>>> GetTask()
        {
            if (!this.IsAuth())
                return NotFound();
            return await _context.Task.ToListAsync();
        }

        [HttpGet("{id}")]
        [Route("getWorkerTask")]
        public async Task<ActionResult<IEnumerable<CommonLibrary.Models.Task>>> GetWorkerTask(int id)
        {
            if (!this.IsAuth())
                return NotFound();
            return await _context.Task.Where(x => x.Worker == id && x.TaskStatus == CommonLibrary.TaskStatus.InProgress).ToListAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<CommonLibrary.Models.Task>> GetTask(int id)
        {
            if (!this.IsAuth())
                return NotFound();
            var task = await _context.Task.FindAsync(id);

            if (task == null)
            {
                return NotFound();
            }

            return task;
        }

        [HttpPut]
        [Route("edit")]
        public async Task<IActionResult> Edit(CommonLibrary.Models.Task task)
        {
            if (!this.IsAuth())
                return NotFound();
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
        public async Task<ActionResult<CommonLibrary.Models.Task>> Add(CommonLibrary.Models.Task task)
        {
            if (!this.IsAuth())
                return NotFound();
            _context.Task.Add(task);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTask", new { id = task.ID }, task);
        }

        [HttpDelete("{id}")]
        [Route("delete")]
        public async Task<ActionResult<CommonLibrary.Models.Task>> DeleteTask(int id)
        {
            if (!this.IsAuth())
                return NotFound();
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
