using Microsoft.EntityFrameworkCore.Migrations;

namespace TaskAPI.Migrations
{
    public partial class Update3 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_HistoryTasks_HistoryTasks_HistoryTaskID",
                table: "HistoryTasks");

            migrationBuilder.DropIndex(
                name: "IX_HistoryTasks_HistoryTaskID",
                table: "HistoryTasks");

            migrationBuilder.DropColumn(
                name: "HistoryTaskID",
                table: "HistoryTasks");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "HistoryTaskID",
                table: "HistoryTasks",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_HistoryTasks_HistoryTaskID",
                table: "HistoryTasks",
                column: "HistoryTaskID");

            migrationBuilder.AddForeignKey(
                name: "FK_HistoryTasks_HistoryTasks_HistoryTaskID",
                table: "HistoryTasks",
                column: "HistoryTaskID",
                principalTable: "HistoryTasks",
                principalColumn: "ID",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
