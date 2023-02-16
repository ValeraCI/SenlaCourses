package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.LoginDetailsDao;
import senla.dao.RoleDao;
import senla.dto.account.*;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.Album;
import senla.models.LoginDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{
    private final AccountDao accountDao;
    private final AlbumDao albumDao;
    private final LoginDetailsDao loginDetailsDao;
    private final RoleDao roleDao;

    @Override
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

    @Override
    public AccountMainDataDto findAccountMainDataDtoById(Long id) {
       Account account = accountDao.findById(id);
       AccountMainDataDto accountMainDataDto = new AccountMainDataDto();
       accountMainDataDto.setId(account.getId());
       accountMainDataDto.setNickname(account.getNickname());
       accountMainDataDto.setRoleTitle(account.getRole().getRoleTitle().toString());

       return accountMainDataDto;
    }

    @Override
    public List<AccountMainDataDto> findAllAccountMainDataDto() {
        List<AccountMainDataDto> accountMainDataDtoList = new ArrayList<>();

        for (Account account: accountDao.findAll()) {
            AccountMainDataDto accountMainDataDto = new AccountMainDataDto();
            accountMainDataDto.setId(account.getId());
            accountMainDataDto.setNickname(account.getNickname());

            accountMainDataDto.setRoleTitle(account.getRole().getRoleTitle().toString());
            accountMainDataDtoList.add(accountMainDataDto);
        }

        return accountMainDataDtoList;
    }

    @Override
    public AccountWithLoginDetailsDto findAccountWithLoginDetailsDtoByEmail(String email){
        Account account = accountDao.findByEmail(email);
        AccountWithLoginDetailsDto accountWithLoginDetailsDto = new AccountWithLoginDetailsDto();

        accountWithLoginDetailsDto.setEmail(email);
        accountWithLoginDetailsDto.setNickname(account.getNickname());
        accountWithLoginDetailsDto.setId(account.getId());
        accountWithLoginDetailsDto.setRole(account.getRole().getRoleTitle());
        accountWithLoginDetailsDto.setPassword(account.getLoginDetails().getPassword());

        return accountWithLoginDetailsDto;
    }

    @Override
    public void updateData(Long id, UpdateAccountDto accountUpdateDto){
        Account account = new Account();
        account.setId(id);
        account.setNickname(accountUpdateDto.getNickname());
        account.setRole(roleDao.findById(accountUpdateDto.getRoleId()));
        accountDao.update(account);

        LoginDetails loginDetails =
                new LoginDetails(account, null, accountUpdateDto.getPassword());
        loginDetailsDao.update(loginDetails);
    }

    @Override
    public void deleteById(Long id){
        accountDao.deleteById(id);
    }

    @Override
    public void addSavedAlbum(Long accountId, Long albumId){
        Account account = accountDao.findWithSavedAlbums(accountId);
        Album album = albumDao.findById(albumId);

        if(!account.getSavedAlbums().contains(album)){
            account.getSavedAlbums().add(album);
        }
        else {
            throw new DataChangesException("Альбом уже сохранён");
        }
    }

    @Override
    public void removeSavedAlbum(Long accountId, Long albumId){
        Account account = accountDao.findWithSavedAlbums(accountId);
        Album album = albumDao.findById(albumId);

        if(account.getSavedAlbums().contains(album)){
            account.getSavedAlbums().remove(album);
        }
        else {
            throw new DataChangesException("Альбом не сохранён");
        }
    }
}
