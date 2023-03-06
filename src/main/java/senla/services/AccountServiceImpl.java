package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.RoleDao;
import senla.dto.RegistrationRequest;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDataDto;
import senla.dto.account.UpdateAccountRoleDto;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.Album;
import senla.models.Role;
import senla.models.RoleTitle;
import senla.services.api.AccountService;
import senla.util.mappers.AccountMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final AlbumDao albumDao;
    private final RoleDao roleDao;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long save(RegistrationRequest accountDataDto) {
        Account account = accountMapper.toEntity(accountDataDto);

        account.getLoginDetails().setPassword(
                passwordEncoder.encode( account.getLoginDetails().getPassword())
        );

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
    public void updateData(Long id, UpdateAccountDataDto accountUpdateDto) {
        Account account = accountDao.findById(id);

        String password = passwordEncoder.encode(accountUpdateDto.getPassword()) ;
        account.getLoginDetails().setPassword(password);
        account.setNickname(accountUpdateDto.getNickname());
        accountDao.update(account);
    }
    
    @Override
    public void updateRole(Long id, UpdateAccountRoleDto accountUpdateDto) {
        Account account = accountDao.findById(id);

        if(account.getRole().getRoleTitle() == RoleTitle.OWNER){
            throw new DataChangesException("You cannot change the \"OWNER\" role");
        }

        Role role = roleDao.findById(accountUpdateDto.getRoleId());

        if(role.getRoleTitle() == RoleTitle.OWNER){
            Account owner = accountDao.findByRole(RoleTitle.OWNER);
            owner.setRole(roleDao.findById(2L));
            accountDao.update(owner);
        }

        account.setRole(role);
        accountDao.update(account);
    }

    @Override
    public void deleteById(Long id) {
        accountDao.deleteById(id);
    }

    @Override
    public void addSavedAlbum(Long accountId, Long albumId) {
        Account account = accountDao.findWithSavedAlbums(accountId);
        Album album = albumDao.findById(albumId);

        if (!account.getSavedAlbums().contains(album)) {
            account.getSavedAlbums().add(album);
        } else {
            throw new DataChangesException("Album has already been saved");
        }
    }

    @Override
    public void removeSavedAlbum(Long accountId, Long albumId) {
        Account account = accountDao.findWithSavedAlbums(accountId);
        Album album = albumDao.findById(albumId);

        if (account.getSavedAlbums().contains(album)) {
            account.getSavedAlbums().remove(album);
        } else {
            throw new DataChangesException("Album not saved");
        }
    }
}
