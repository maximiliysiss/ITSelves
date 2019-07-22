using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using CommonLibrary.Models;
using CommonActions;
using Newtonsoft.Json;
using CommonActionsWeb;
using Microsoft.AspNetCore.Http;
using System.IO;
using System.Text;

namespace WebClient.Controllers
{
    public class TasksController : Controller
    {
        NetConnection NetConnection = new NetConnection { BaseURL = Configuration.URLS["Task"] };
        NetConnection NetWorkers = new NetConnection { BaseURL = Configuration.URLS["Auth"] };

        // GET: Tasks
        public async Task<IActionResult> Index()
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            return View(await NetConnection.LoadObjectByGet<List<CommonLibrary.Models.Task>>("/Tasks", token: HttpContext.Session.GetString("Token"))
                ?? new List<CommonLibrary.Models.Task>());
        }

        // GET: Tasks/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id == null)
            {
                return NotFound();
            }

            var task = await NetConnection.LoadObjectByGet<CommonLibrary.Models.Task>($"/Tasks/{id}");
            if (task == null)
            {
                return NotFound();
            }

            return View("Create", task);
        }

        // GET: Tasks/Create
        public async Task<IActionResult> Create()
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            ViewBag.User = this.GetUserByToken(HttpContext.Session.GetString("Token")).Name;
            ViewBag.Workers = await NetWorkers.LoadObjectByGet<List<Worker>>("/worker/", "", HttpContext.Session.GetString("Token"));
            return View(new CommonLibrary.Models.Task());
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create(CommonLibrary.Models.Task task)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (task.PhotoStart != null)
            {
                task.Photos.Add(new Photo { Image = Encoding.ASCII.GetBytes(new StreamReader(task.PhotoStart.OpenReadStream()).ReadToEnd()) });
            }
            var adress = this.Request.Form["house"].ToString();
            task.House = (await new NetConnection { BaseURL = Configuration.URLS["HCS"] }.LoadObjectByGet<List<House>>($"/Houses"))
                .FirstOrDefault(x => x.ToString().Contains(adress)).ID;
            await NetConnection.LoadObjectByPost<CommonLibrary.Models.Task>("/Tasks/Add", JsonConvert.SerializeObject(task),
                HttpContext.Session.GetString("Token"));
            return RedirectToAction(nameof(Index));
        }

        public async Task<IActionResult> Edit(int? id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id == null)
            {
                return NotFound();
            }

            var task = await NetConnection.LoadObjectByGet<CommonLibrary.Models.Task>($"/Tasks/{id}");
            if (task == null)
            {
                return NotFound();
            }
            return View(task);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("ID,DateTime,Name,TaskStatus,Description,Category,House,Worker,Rate,Comment")] CommonLibrary.Models.Task task)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id != task.ID)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    await NetConnection.LoadObjectByPost<CommonLibrary.Models.Task>("/Tasks/Edit", JsonConvert.SerializeObject(task));
                }
                catch (DbUpdateConcurrencyException)
                {
                    return NotFound();
                }
                return RedirectToAction(nameof(Index));
            }
            return View(task);
        }

        // GET: Tasks/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            if (id == null)
            {
                return NotFound();
            }

            var task = await NetConnection.LoadObjectByGet<CommonLibrary.Models.Task>($"/Tasks/{id}");
            if (task == null)
            {
                return NotFound();
            }

            return View(task);
        }

        // POST: Tasks/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (!this.IsAuth())
                return RedirectToAction("Index", "Auth");
            await NetConnection.LoadObjectByDelete<CommonLibrary.Models.Task>($"/Tasks/Delete/{id}");
            return RedirectToAction(nameof(Index));
        }
    }
}
