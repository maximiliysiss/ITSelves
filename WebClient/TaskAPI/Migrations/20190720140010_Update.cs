using Microsoft.EntityFrameworkCore.Migrations;

namespace TaskAPI.Migrations
{
    public partial class Update : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<int>(
                name: "Worker",
                table: "Task",
                nullable: false,
                oldClrType: typeof(string),
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "House",
                table: "Task",
                nullable: false,
                oldClrType: typeof(string),
                oldNullable: true);

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

        protected override void Down(MigrationBuilder migrationBuilder)
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

            migrationBuilder.AlterColumn<string>(
                name: "Worker",
                table: "Task",
                nullable: true,
                oldClrType: typeof(int));

            migrationBuilder.AlterColumn<string>(
                name: "House",
                table: "Task",
                nullable: true,
                oldClrType: typeof(int));
        }
    }
}
