using CAMS.Domain;
using CAMS.Persistence;
using Microsoft.EntityFrameworkCore;

namespace CAMS.Repositories
{
    public class AccountRepository : IAccountRepository
    {
        private readonly AppDBContext _context;

        public AccountRepository(AppDBContext context)
        {
            _context = context;
        }
        public async Task<IEnumerable<Account>> GetAllAccounts(bool isPrime)
        {
            var query = _context.Accounts.AsQueryable();

            if (isPrime)
            {
                query = query.Where(a => a.Balance > 10000.00m);
            }

            return await query
                .Include(a => a.Owner)
                .OrderByDescending(a => a.Balance)
                .ToListAsync();
        }
    }
}