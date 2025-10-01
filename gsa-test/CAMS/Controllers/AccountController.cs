using CAMS.Services;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace CAMS.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AccountController : ControllerBase
    {

        private readonly IAccountService _accountService;
        public AccountController(IAccountService accountService)
        {
            _accountService = accountService;
        }
        // GET: api/account/list
        [HttpGet("list")]
        public async Task<IActionResult> GetAccountList()
        {
            var accounts = await _accountService.GetAllAccounts(false);
            return Ok(accounts);
        }

        // GET: api/account/prime/list
        [HttpGet("prime/list")]
        public async Task<IActionResult> GetPrimeAccountList()
        {
            var primeAccounts = await _accountService.GetAllAccounts(true);
            return Ok(primeAccounts);
        }
    }
}