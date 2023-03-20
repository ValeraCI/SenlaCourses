package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.RoleDao;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.RegistrationRequest;
import senla.dto.account.UpdateAccountDataDto;
import senla.dto.account.UpdateAccountRoleDto;
import senla.exceptions.DataChangesException;
import senla.exceptions.InsufficientRightsException;
import senla.models.Account;
import senla.models.AccountDetails;
import senla.models.Album;
import senla.models.Role;
import senla.models.RoleTitle;
import senla.services.api.AccountService;
import senla.util.Convertor;
import senla.util.Paginator;
import senla.util.mappers.AccountMapper;

import java.util.List;
import java.util.Objects;

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
                passwordEncoder.encode(account.getLoginDetails().getPassword())
        );

        return accountDao.save(account);
    }

    @Override
    public AccountMainDataDto findAccountMainDataDtoById(Long id) {
        Account account = accountDao.findById(id);

        return accountMapper.toAccountMainDataDto(account);
    }


    @Override
    public List<AccountMainDataDto> findAllAccountMainDataDto(String pageNumber, String limit) {
        Integer pageNumberInteger = Convertor.stringToInteger(pageNumber);
        Integer limitInteger = Convertor.stringToInteger(limit);

        limitInteger = Paginator.limitingMinimumValueToOne(limitInteger);

        Long totalCount = accountDao.getTotalCount();
        Integer firstResult = Paginator.getFirstElement(pageNumberInteger, totalCount, limitInteger);

        return accountMapper.toAccountMainDataDtoList(
                accountDao.findAll(Math.toIntExact(firstResult), limitInteger)
        );
    }

    @Override
    public void updateData(Long id, UpdateAccountDataDto accountUpdateDto, AccountDetails accountDetails) {
        if (!hasAccess(id, accountDetails)) {
            throw new InsufficientRightsException("You can't update this user");
        }

        Account account = accountDao.findById(id);

        String password = passwordEncoder.encode(accountUpdateDto.getPassword());
        account.getLoginDetails().setPassword(password);
        account.setNickname(accountUpdateDto.getNickname());
        accountDao.update(account);
    }

    @Override
    public void updateRole(Long id, UpdateAccountRoleDto accountUpdateDto) {
        Account account = accountDao.findById(id);

        if (account.getRole().getRoleTitle() == RoleTitle.ROLE_OWNER) {
            throw new DataChangesException("You can't change the 'OWNER' role");
        }

        Role role = roleDao.findById(accountUpdateDto.getRoleId());

        if (role.getRoleTitle() == RoleTitle.ROLE_OWNER) {
            Account owner = accountDao.findByRole(RoleTitle.ROLE_OWNER);
            owner.setRole(roleDao.findById(2L));
            accountDao.update(owner);
        }

        account.setRole(role);
        accountDao.update(account);
    }

    @Override
    public void deleteById(Long id, AccountDetails accountDetails) {
        if (!hasAccess(id, accountDetails)) {
            throw new InsufficientRightsException("You can't delete this user");
        }

        accountDao.deleteById(id);
    }

    @Override
    public void addSavedAlbum(Long accountId, Long albumId, AccountDetails accountDetails) {
        if (!hasAccess(accountId, accountDetails)) {
            throw new InsufficientRightsException("You can't add an album to this user");
        }

        Account account = accountDao.findWithSavedAlbumsById(accountId);
        Album album = albumDao.findById(albumId);

        if (!account.getSavedAlbums().contains(album)) {
            account.getSavedAlbums().add(album);
        } else {
            throw new DataChangesException("Album has already been saved");
        }
    }

    @Override
    public void removeSavedAlbum(Long accountId, Long albumId, AccountDetails accountDetails) {
        if (!hasAccess(accountId, accountDetails)) {
            throw new InsufficientRightsException("You can't delete this user's album");
        }

        Account account = accountDao.findWithSavedAlbumsById(accountId);
        Album album = albumDao.findById(albumId);

        if (account.getSavedAlbums().contains(album)) {
            account.getSavedAlbums().remove(album);
        } else {
            throw new DataChangesException("Album not saved");
        }
    }

    private boolean hasAccess(Long id, AccountDetails accountDetails) {
        return Objects.equals(id, accountDetails.getId()) ||
                accountDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(auth -> auth.equals(RoleTitle.ROLE_ADMINISTRATOR.toString())
                                || auth.equals(RoleTitle.ROLE_OWNER.toString()));
    }
}
