using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace TaskAPI.Migrations
{
    public partial class Update4 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<DateTime>(
                name: "TimeWork",
                table: "Task",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<int>(
                name: "TaskID1",
                table: "Photos",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Photos_TaskID1",
                table: "Photos",
                column: "TaskID1");

            migrationBuilder.AddForeignKey(
                name: "FK_Photos_Task_TaskID1",
                table: "Photos",
                column: "TaskID1",
                principalTable: "Task",
                principalColumn: "ID",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Photos_Task_TaskID1",
                table: "Photos");

            migrationBuilder.DropIndex(
                name: "IX_Photos_TaskID1",
                table: "Photos");

            migrationBuilder.DropColumn(
                name: "TimeWork",
                table: "Task");

            migrationBuilder.DropColumn(
                name: "TaskID1",
                table: "Photos");
        }
    }
}
