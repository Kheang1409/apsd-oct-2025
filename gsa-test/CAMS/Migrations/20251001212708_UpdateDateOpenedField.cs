using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CAMS.Migrations
{
    /// <inheritdoc />
    public partial class UpdateDateOpenedField : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "DateOpen",
                table: "Accounts",
                newName: "DateOpened");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "DateOpened",
                table: "Accounts",
                newName: "DateOpen");
        }
    }
}
