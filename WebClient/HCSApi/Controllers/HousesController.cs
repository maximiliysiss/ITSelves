using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using HCSApi;
using CommonLibrary.Models;

namespace HCSApi.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class HousesController : ControllerBase
    {
        private readonly CommonLibrary.Databases.DatabaseHCSApi _context;

        public HousesController(CommonLibrary.Databases.DatabaseHCSApi context)
        {
            _context = context;
        }

        // GET: api/Houses
        [HttpGet]
        public async Task<ActionResult<IEnumerable<House>>> GetHouse()
        {
            return await _context.House.ToListAsync();
        }

        // GET: api/Houses/5
        [HttpGet("{id}")]
        public async Task<ActionResult<House>> GetHouse(int id)
        {
            var house = await _context.House.FindAsync(id);

            if (house == null)
            {
                return NotFound();
            }

            return house;
        }

        // PUT: api/Houses/5
        [HttpPut]
        [Route("edit")]
        public async Task<IActionResult> Edit(House house)
        {
            _context.Entry(house).State = EntityState.Modified;

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

        // POST: api/Houses
        [HttpPost]
        [Route("add")]
        public async Task<ActionResult<House>> Add(House house)
        {
            _context.House.Add(house);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetHouse", new { id = house.ID }, house);
        }

        // DELETE: api/Houses/5
        [HttpDelete("{id}")]
        [Route("delete")]
        public async Task<ActionResult<House>> DeleteHouse(int id)
        {
            var house = await _context.House.FindAsync(id);
            if (house == null)
            {
                return NotFound();
            }

            _context.House.Remove(house);
            await _context.SaveChangesAsync();

            return house;
        }
    }
}
