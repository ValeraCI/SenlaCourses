package senla.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.exceptions.ObjectNotFoundException;
import senla.dao.AccountDao;
import senla.dao.LoginDetailsDao;
import senla.dto.AccountDto;
import senla.dto.LoginDetailsDto;
import senla.models.Account;
import senla.models.Album;
import senla.models.LoginDetails;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private AccountDao accountDao;
    private LoginDetailsDao loginDetailsDao;
    private AlbumService albumService;

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Autowired
    public void setLoginDetailsDao(LoginDetailsDao loginDetailsDao) {
        this.loginDetailsDao = loginDetailsDao;
    }

    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void add(AccountDto accountDto, LoginDetailsDto loginDetailsDto){
        Account account = new Account(accountDto.getId(), accountDto.getNickname(), new Date(),
                accountDto.getRole(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        LoginDetails loginDetails = new LoginDetails(account, loginDetailsDto.getEmail(),
                loginDetailsDto.getPassword());
        accountDao.add(account);
        loginDetailsDao.add(loginDetails);
    }

    public Account getAccountById(long id){
        Optional<Account> optionalAccount = accountDao.getById(id);

        if(optionalAccount.isEmpty()){
            String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        return optionalAccount.get();
    }

    public AccountDto getAccountDtoById(long id) {
        Account account = getAccountById(id);

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
        accountDao.delete(accountId);
        loginDetailsDao.delete(accountId);
    }

    public LoginDetails getLoginDetailsById(long id){
        Optional<LoginDetails> optionalAccount = loginDetailsDao.getById(id);

        if(optionalAccount.isEmpty()){
            String errorMassage = "Данных аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        return optionalAccount.get();
    }

    public LoginDetailsDto getLoginDetailsDtoById(long id) {
        LoginDetails loginDetails = getLoginDetailsById(id);

        LoginDetailsDto loginDetailsDto = new LoginDetailsDto(loginDetails.getEmail(), loginDetails.getPassword());
        return loginDetailsDto;
    }

    public void addSavedAlbum(long id, long albumId){
        Album album = albumService.getAlbumById(albumId);
        accountDao.addSavedAlbum(id, album);
    }

    public void removeSavedAlbum(long id, long albumId){
        Album album = albumService.getAlbumById(albumId);
        accountDao.removeSavedAlbum(id, album);
    }

    public void addCreatedAlbum(long id, long albumId){
        Album album = albumService.getAlbumById(albumId);

        accountDao.addCreatedAlbum(id, album);
    }

    public void removeCreatedAlbum(long id, long albumId){
        Album album = albumService.getAlbumById(albumId);

        accountDao.removeCreatedAlbum(id, album);
    }
}
