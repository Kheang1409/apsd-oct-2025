using CAMS.Domain;

namespace CAMS.Services
{
    public interface IAccountService
    {
        Task<IEnumerable<Account>> GetAllAccounts(bool isPrime);
    }
}