using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using CommonLibrary.Models;
using CommonActions;
using System.Net.Http;
using Newtonsoft.Json;
using CommonActionsWeb;

namespace WebClient.Controllers
{
    public class SchedulesController : Controller
    {
        NetConnection NetConnection = new NetConnection { BaseURL = Configuration.URLS["Schedule"] };
        NetConnection Net = new NetConnection { BaseURL = Configuration.URLS["Auth"] };
        // GET: Schedules
        public async Task<IActionResult> Index()
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            return View(await NetConnection.LoadObjectByGet<List<Schedule>>("/Schedules") ?? new List<Schedule>());
        }

        // GET: Schedules/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id == null)
            {
                return NotFound();
            }

            var schedule = await NetConnection.LoadObjectByGet<Schedule>($"/Schedules/{id}");
            if (schedule == null)
            {
                return NotFound();
            }

            return View(schedule);
        }

        // GET: Schedules/Create
        public async Task<IActionResult> Create()
        {
            ViewBag.Workers = await Net.LoadObjectByGet<List<Worker>>("/worker/");
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("ID,Worker,DayOfWeek,StartTime,EndTime")] Schedule schedule)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (ModelState.IsValid)
            {
                await NetConnection.LoadObjectByPost<Schedule>("/Schedules/Add", JsonConvert.SerializeObject(schedule));
                return RedirectToAction(nameof(Index));
            }
            return View(schedule);
        }

        // GET: Schedules/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id == null)
            {
                return NotFound();
            }

            var schedule = await NetConnection.LoadObjectByGet<Schedule>($"/Schedules/{id}");
            if (schedule == null)
            {
                return NotFound();
            }
            return View(schedule);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("ID,Worker,DayOfWeek,StartTime,EndTime")] Schedule schedule)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id != schedule.ID)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    await NetConnection.LoadObjectByPost<Schedule>("/Schedules/Edit", JsonConvert.SerializeObject(schedule));
                }
                catch (DbUpdateConcurrencyException)
                {
                    return NotFound();
                }
                return RedirectToAction(nameof(Index));
            }
            return View(schedule);
        }

        // GET: Schedules/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id == null)
            {
                return NotFound();
            }

            var schedule = await NetConnection.LoadObjectByGet<Schedule>($"/Schedules/{id}");
            if (schedule == null)
            {
                return NotFound();
            }

            return View(schedule);
        }

        // POST: Schedules/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            var schedule = await NetConnection.LoadObjectByDelete<Schedule>($"/Delete/{id}");
            return RedirectToAction(nameof(Index));
        }
    }
}
