using CAMS.Domain;

namespace CAMS.Repositories
{
    public interface IAccountRepository
    {
        Task<IEnumerable<Account>> GetAllAccounts(bool isPrime);
    }
}