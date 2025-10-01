using System;

namespace CAMS.Domain
{
    public class Account
    {
        public long AccountId { get; private set; }
        public string AccountNumber { get; private set; } = string.Empty;
        public string AccountType { get; private set; } = string.Empty;
        public DateTime DateOpened { get; private set; }
        public decimal Balance { get; private set; }
        public long OwnerId { get; private set; }
        public Customer Owner { get; private set; } = null!;
    }
}