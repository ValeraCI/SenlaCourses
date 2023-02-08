package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.LoginDetailsDao;
import senla.dao.RoleDao;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.models.Account;
import senla.models.LoginDetails;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountDao accountDao;
    private final LoginDetailsDao loginDetailsDao;
    private final RoleDao roleDao;

    public Long save(AccountDataDto accountDataDto){
        Account account = new Account();
        account.setNickname(accountDataDto.getNickname());
        LoginDetails loginDetails =
                new LoginDetails(account, accountDataDto.getEmail(), accountDataDto.getPassword());
        account.setLoginDetails(loginDetails);
        account.setRegistrationDate(LocalDate.now());
        account.setRole(roleDao.findById(3L));
        return accountDao.save(account);
    }

    public AccountMainDataDto getAccountDtoById(Long id) {
       Account account = accountDao.findById(id);
       AccountMainDataDto accountMainDataDto = new AccountMainDataDto();
       accountMainDataDto.setId(account.getId());
       accountMainDataDto.setNickname(account.getNickname());
       accountMainDataDto.setRoleTitle(account.getRole().getRoleTitle().toString());

       return accountMainDataDto;
    }

    public AccountWithLoginDetailsDto getAccountWithLoginDetailsDtoByEmail(String email){
        Account account = accountDao.findByEmail(email);
        AccountWithLoginDetailsDto accountWithLoginDetailsDto = new AccountWithLoginDetailsDto();

        accountWithLoginDetailsDto.setEmail(email);
        accountWithLoginDetailsDto.setNickname(account.getNickname());
        accountWithLoginDetailsDto.setId(account.getId());
        accountWithLoginDetailsDto.setRole(account.getRole().getRoleTitle());
        accountWithLoginDetailsDto.setPassword(account.getLoginDetails().getPassword());

        return accountWithLoginDetailsDto;
    }

    public void updateData(AccountDto accountDto){
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setNickname(accountDto.getNickname());
        account.setRole(roleDao.findById(accountDto.getRoleId()));
        accountDao.update(account);

        LoginDetails loginDetails =
                new LoginDetails(account, null, accountDto.getPassword());
        loginDetailsDao.update(loginDetails);
    }

    public void deleteById(long id){
        accountDao.deleteById(id);
    }
}
