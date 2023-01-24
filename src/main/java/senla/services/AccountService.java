package senla.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.Exceptions.ObjectsNotLinkedException;
import senla.dao.AccountDao;
import senla.dao.LoginDetailsDao;
import senla.dto.AccountDto;
import senla.dto.LoginDetailsDto;
import senla.models.Account;
import senla.models.LoginDetails;
import java.util.ArrayList;
import java.util.Date;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private AccountDao accountDao;
    private LoginDetailsDao loginDetailsDao;

    @Autowired
    public AccountService(AccountDao accountDao, LoginDetailsDao loginDetailsDao) {
        this.accountDao = accountDao;
        this.loginDetailsDao = loginDetailsDao;
    }

    public void add(AccountDto accountDto, LoginDetailsDto loginDetailsDto){
        if(accountDto.getId() != loginDetailsDto.getAccountId()) {
            String errorMessage = "У объектов должен быть одинаковый индекс";
            logger.error(errorMessage);
            throw new ObjectsNotLinkedException(errorMessage);
        }
        Account account = new Account(accountDto.getId(), accountDto.getNickname(), new Date(),
                accountDto.getRole(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        LoginDetails loginDetails = new LoginDetails(loginDetailsDto.getAccountId(), loginDetailsDto.getEmail(),
                loginDetailsDto.getPassword());
        accountDao.add(account);
        loginDetailsDao.add(loginDetails);
    }

    public AccountDto getById(long id) {
        Account account = accountDao.getById(id);
        AccountDto accountDto = new AccountDto(id, account.getNickname(), account.getRole());
        return accountDto;
    }

    public void updateData(AccountDto accountDto){
        accountDao.update(accountDto.getId(), accountDto.getNickname(), accountDto.getRole());
    }

    public void updatePassword(long id, String password){
        loginDetailsDao.updatePassword(id, password);
    }

    public void deleteById(long accountId){
        accountDao.deleteById(accountId);
        loginDetailsDao.deleteById(accountId);
    }

    public LoginDetails getLoginDetailsById(long id) {
        return loginDetailsDao.getById(id);
    }

    public void addSavedAlbum(long id, long albumId){
        accountDao.addSavedAlbum(id, albumId);
    }

    public void removeSavedAlbum(long id, long albumId){
        accountDao.removeSavedAlbum(id, albumId);
    }
}
