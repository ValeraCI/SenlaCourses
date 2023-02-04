package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.RoleDao;
import senla.dto.*;
import senla.models.Account;
import senla.models.LoginDetails;
import senla.models.LoginDetails_;
import senla.models.Role;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private AccountDao accountDao;
    private RoleDao roleDao;

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional()
    public void add(CreateAccountDataDto createAccountDataDto){
        Account account = new Account();
        account.setNickname(createAccountDataDto.getNickname());
        LoginDetails loginDetails =
                new LoginDetails(account, createAccountDataDto.getEmail(), createAccountDataDto.getPassword());
        account.setLoginDetails(loginDetails);
        account.setRegistrationDate(new Date());
        account.setRole(roleDao.findById(3l));
        accountDao.save(account);

        throw new RuntimeException("Check");
    }

    public AccountDto getAccountDtoById(long id) {
       Account account = accountDao.findById(id);
       AccountDto accountDto = new AccountDto();
       accountDto.setId(account.getId());
       accountDto.setNickname(account.getNickname());
       accountDto.setRole(account.getRole().getRoleTitle());

       return accountDto;
    }

    @Transactional
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


    @Transactional
    public void updateData(UpdateAccountDto updateAccountDto){

    }

    @Transactional
    public void updatePassword(long id, String password){

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteById(long id){
        accountDao.deleteById(id);
    }
}
