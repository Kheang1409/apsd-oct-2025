using System.Collections.Generic;

namespace CAMS.Domain
{
    public class Customer
    {
        public long CustomerId { get; private set; }
        public string FirstName { get; private set; } = string.Empty;
        public string LastName { get; private set; } = string.Empty;
        public Account Account { get; private set; }
    }
}