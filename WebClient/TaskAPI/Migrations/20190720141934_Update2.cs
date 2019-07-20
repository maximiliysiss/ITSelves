using System;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;

namespace TaskAPI.Migrations
{
    public partial class Update2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Task_Task_OriginalTaskID",
                table: "Task");

            migrationBuilder.DropIndex(
                name: "IX_Task_OriginalTaskID",
                table: "Task");

            migrationBuilder.DropColumn(
                name: "OriginalTaskID",
                table: "Task");

            migrationBuilder.DropColumn(
                name: "Discriminator",
                table: "Task");

            migrationBuilder.AddColumn<int>(
                name: "HistoryTaskID",
                table: "Photo",
                nullable: true);

            migrationBuilder.CreateTable(
                name: "HistoryTasks",
                columns: table => new
                {
                    ID = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn),
                    DateTime = table.Column<DateTime>(nullable: false),
                    TaskStatus = table.Column<int>(nullable: true),
                    Description = table.Column<string>(nullable: true),
                    CategoryID = table.Column<int>(nullable: true),
                    House = table.Column<int>(nullable: false),
                    Worker = table.Column<int>(nullable: false),
                    OriginalTaskID = table.Column<int>(nullable: true),
                    HistoryTaskID = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_HistoryTasks", x => x.ID);
                    table.ForeignKey(
                        name: "FK_HistoryTasks_Category_CategoryID",
                        column: x => x.CategoryID,
                        principalTable: "Category",
                        principalColumn: "ID",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_HistoryTasks_HistoryTasks_HistoryTaskID",
                        column: x => x.HistoryTaskID,
                        principalTable: "HistoryTasks",
                        principalColumn: "ID",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_HistoryTasks_Task_OriginalTaskID",
                        column: x => x.OriginalTaskID,
                        principalTable: "Task",
                        principalColumn: "ID",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Photo_HistoryTaskID",
                table: "Photo",
                column: "HistoryTaskID");

            migrationBuilder.CreateIndex(
                name: "IX_HistoryTasks_CategoryID",
                table: "HistoryTasks",
                column: "CategoryID");

            migrationBuilder.CreateIndex(
                name: "IX_HistoryTasks_HistoryTaskID",
                table: "HistoryTasks",
                column: "HistoryTaskID");

            migrationBuilder.CreateIndex(
                name: "IX_HistoryTasks_OriginalTaskID",
                table: "HistoryTasks",
                column: "OriginalTaskID");

            migrationBuilder.AddForeignKey(
                name: "FK_Photo_HistoryTasks_HistoryTaskID",
                table: "Photo",
                column: "HistoryTaskID",
                principalTable: "HistoryTasks",
                principalColumn: "ID",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Photo_HistoryTasks_HistoryTaskID",
                table: "Photo");

            migrationBuilder.DropTable(
                name: "HistoryTasks");

            migrationBuilder.DropIndex(
                name: "IX_Photo_HistoryTaskID",
                table: "Photo");

            migrationBuilder.DropColumn(
                name: "HistoryTaskID",
                table: "Photo");

            migrationBuilder.AddColumn<int>(
                name: "OriginalTaskID",
                table: "Task",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "Discriminator",
                table: "Task",
                nullable: false,
                defaultValue: "");

            migrationBuilder.CreateIndex(
                name: "IX_Task_OriginalTaskID",
                table: "Task",
                column: "OriginalTaskID");

            migrationBuilder.AddForeignKey(
                name: "FK_Task_Task_OriginalTaskID",
                table: "Task",
                column: "OriginalTaskID",
                principalTable: "Task",
                principalColumn: "ID",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
