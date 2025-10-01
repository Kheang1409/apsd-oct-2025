using CAMS.Domain;
using CAMS.Repositories;

namespace CAMS.Services
{
    public class AccountService : IAccountService
    {
        private readonly IAccountRepository _accountRepository;
        public AccountService(IAccountRepository accountRepository)
        {
            _accountRepository = accountRepository;
        }
        public async Task<IEnumerable<Account>> GetAllAccounts(bool isPrime)
        {
            return await _accountRepository.GetAllAccounts(isPrime);
        }
    }
}