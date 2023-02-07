package senla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.LoginDetailsDao;
import senla.dao.RoleDao;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.dto.album.AlbumInfoDto;
import senla.models.Account;
import senla.models.Album;
import senla.models.LoginDetails;

import java.util.*;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountDao accountDao;
    private AlbumDao albumDao;
    private LoginDetailsDao loginDetailsDao;
    private RoleDao roleDao;

    @Transactional
    public void save(AccountDataDto accountDataDto){
        Account account = new Account();
        account.setNickname(accountDataDto.getNickname());
        LoginDetails loginDetails =
                new LoginDetails(account, accountDataDto.getEmail(), accountDataDto.getPassword());
        account.setLoginDetails(loginDetails);
        account.setRegistrationDate(new Date());
        account.setRole(roleDao.findById(3L));
        accountDao.save(account);
        loginDetailsDao.save(loginDetails);
    }

    @Transactional
    public AccountMainDataDto getAccountDtoById(Long id) {
       Account account = accountDao.findById(id);
       AccountMainDataDto accountMainDataDto = new AccountMainDataDto();
       accountMainDataDto.setId(account.getId());
       accountMainDataDto.setNickname(account.getNickname());
       accountMainDataDto.setRoleTitle(account.getRole().getRoleTitle().toString());

       return accountMainDataDto;
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

    private Set<AlbumInfoDto> albumSerToAlbumInfoDtoSet(Set<Album> albums){
        Set<AlbumInfoDto> savedAlbumsInfoDto = new HashSet<>();

        for (Album album: albums) {
            AlbumInfoDto albumInfoDto = new AlbumInfoDto();
            albumInfoDto.setId(album.getId());
            albumInfoDto.setTitle(album.getTitle());

            savedAlbumsInfoDto.add(albumInfoDto);
        }

        return savedAlbumsInfoDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteById(long id){
        accountDao.deleteById(id);
    }

    @Transactional
    public Set<AlbumInfoDto> getSavedAlbumsById(Long id){
        Set<Album> savedAlbums = accountDao.findSavedAlbumsById(id);
        return albumSerToAlbumInfoDtoSet(savedAlbums);
    }

    @Transactional
    public void addSavedAlbum(Long accountId, Long albumId){
        Album album = albumDao.findById(albumId);

        accountDao.addSavedAlbum(accountId, album);
    }

    @Transactional
    public void removeSavedAlbum(Long accountId, Long albumId){
        Album album = albumDao.findById(albumId);

        accountDao.removeSavedAlbum(accountId, album);
    }

    @Transactional
    public Set<AlbumInfoDto> findCreatedAlbumsById(Long id){
        Set<Album> createdAlbums = accountDao.findCreatedAlbumsById(id);

        return albumSerToAlbumInfoDtoSet(createdAlbums);
    }
}
