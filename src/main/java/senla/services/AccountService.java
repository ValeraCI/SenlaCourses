package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.AccountDao;
import senla.dto.*;
import senla.annotations.Transaction;

import java.util.Date;

@Service
public class AccountService {
    private AccountDao accountDao;

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Transaction
    public void add(CreateAccountDataDto createAccountDataDto){
        CreateAccountDto createAccountDto = new CreateAccountDto(createAccountDataDto.getNickname(), new Date());

        long id = accountDao.addAccount(createAccountDto);
        CreateLoginDetailsDto loginDetailsDto = new CreateLoginDetailsDto(id, createAccountDataDto.getEmail(),
                createAccountDataDto.getPassword());
        accountDao.addLoginDetails(loginDetailsDto);
    }


    @Transaction
    public AccountDto getAccountDtoById(long id) {
        return accountDao.getAccountDtoById(id);
    }

    @Transaction
    public AccountWithLoginDetailsDto getAccountWithLoginDetailsDtoByEmail(String email) {
        return accountDao.getAccountWithLoginDetailsDtoByEmail(email);
    }

    @Transaction
    public void updateData(UpdateAccountDto updateAccountDto){
        accountDao.update(updateAccountDto);
    }

    @Transaction
    public void updatePassword(long id, String password){
        accountDao.updatePasswordById(id, password);
    }

    @Transaction
    public void deleteById(long accountId){
      accountDao.deleteById(accountId);
    }
}
