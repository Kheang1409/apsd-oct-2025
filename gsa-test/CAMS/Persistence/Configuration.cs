using CAMS.Domain;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace CAMS.Persistence
{
    public class CustomerConfiguration : IEntityTypeConfiguration<Customer>
    {
        public void Configure(EntityTypeBuilder<Customer> builder)
        {
            // Table name
            builder.ToTable("Customers");

            // Primary key
            builder.HasKey(c => c.CustomerId);

            // Properties
            builder.Property(c => c.FirstName)
                .IsRequired()
                .HasMaxLength(50);

            builder.Property(c => c.LastName)
                .IsRequired()
                .HasMaxLength(50);

            // One-to-one relationship: Customer -> Accounts
            builder.HasOne(c => c.Account)
                .WithOne(a => a.Owner)
                .HasForeignKey<Account>(a => a.OwnerId)
                .OnDelete(DeleteBehavior.Cascade);
        }
    }

    public class AccountConfiguration : IEntityTypeConfiguration<Account>
    {
        public void Configure(EntityTypeBuilder<Account> builder)
        {
            builder.ToTable("Accounts");
            
            builder.HasKey(a => a.AccountId);
            
            builder.Property(a => a.AccountNumber)
                .IsRequired()
                .HasMaxLength(20);
                
            builder.Property(a => a.AccountType)
                .IsRequired()
                .HasMaxLength(50);
                
            builder.Property(a => a.DateOpened)
                .IsRequired();
                
            builder.Property(a => a.Balance)
                .HasPrecision(18, 2)
                .IsRequired();
            
            builder.Property(a => a.OwnerId)
                .IsRequired();
            
            builder.HasIndex(a => a.OwnerId);

            builder.HasIndex(a => a.AccountNumber)
                .IsUnique();
        }
    }
}
