using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CAMS.Migrations
{
    /// <inheritdoc />
    public partial class UpdateAccountNumberField : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "AccountIdNumber",
                table: "Accounts",
                newName: "AccountNumber");

            migrationBuilder.RenameIndex(
                name: "IX_Accounts_AccountIdNumber",
                table: "Accounts",
                newName: "IX_Accounts_AccountNumber");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "AccountNumber",
                table: "Accounts",
                newName: "AccountIdNumber");

            migrationBuilder.RenameIndex(
                name: "IX_Accounts_AccountNumber",
                table: "Accounts",
                newName: "IX_Accounts_AccountIdNumber");
        }
    }
}
