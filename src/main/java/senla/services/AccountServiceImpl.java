package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.LoginDetailsDao;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.dto.account.UpdateAccountDto;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.Album;
import senla.models.LoginDetails;
import senla.services.api.AccountService;
import senla.util.mappers.AccountMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final AlbumDao albumDao;
    private final LoginDetailsDao loginDetailsDao;
    private final AccountMapper accountMapper;

    @Override
    public Long save(AccountDataDto accountDataDto){
        Account account = accountMapper.toEntity(accountDataDto);

        return accountDao.save(account);
    }

    @Override
    public AccountMainDataDto findAccountMainDataDtoById(Long id) {
       Account account = accountDao.findById(id);

        AccountMainDataDto accountMainDataDto = accountMapper.toAccountMainDataDto(account);
        return accountMainDataDto;
    }

    @Override
    public List<AccountMainDataDto> findAllAccountMainDataDto() {
        List<AccountMainDataDto> accountMainDataDtoList =
                accountMapper.toAccountMainDataDtoList(accountDao.findAll());

        return accountMainDataDtoList;
    }

    @Override
    public AccountWithLoginDetailsDto findAccountWithLoginDetailsDtoByEmail(String email){
        Account account = accountDao.findByEmail(email);

        AccountWithLoginDetailsDto accountWithLoginDetailsDto
                = accountMapper.toAccountWithLoginDetailsDto(account);

        return accountWithLoginDetailsDto;
    }

    @Override
    public void updateData(Long id, UpdateAccountDto accountUpdateDto){
        Account account = accountMapper.toEntity(accountUpdateDto);
        account.setId(id);
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
