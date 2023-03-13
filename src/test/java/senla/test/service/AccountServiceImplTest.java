package senla.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.configuration.WebMvcConfig;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.RoleDao;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.RegistrationRequest;
import senla.dto.account.UpdateAccountDataDto;
import senla.dto.account.UpdateAccountRoleDto;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.AccountDetails;
import senla.models.Album;
import senla.models.LoginDetails;
import senla.models.Role;
import senla.models.RoleTitle;
import senla.services.AccountServiceImpl;
import senla.test.util.ObjectCreator;
import senla.util.mappers.AccountMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class AccountServiceImplTest {

    @Mock
    private AccountDao accountDao;
    @Mock
    private AlbumDao albumDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccountMapper accountMapper;

    private AccountServiceImpl accountService;

    public AccountServiceImplTest() {
        MockitoAnnotations.openMocks(this);

        accountService = new AccountServiceImpl(accountDao, albumDao, roleDao,
                accountMapper, passwordEncoder, 10);
    }

    @Test
    public void testSave() {
        RegistrationRequest accountDataDto = new RegistrationRequest();
        Account account = ObjectCreator.createAccount();

        when(accountMapper.toEntity(accountDataDto)).thenReturn(account);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(accountDao.save(account)).thenReturn(1L);

        Long id = accountService.save(accountDataDto);

        assertEquals(Long.valueOf(1), id);
        verify(accountDao).save(account);
        verify(passwordEncoder).encode("1234");
    }

    @Test
    public void testFindAccountMainDataDtoById() {
        Account account = new Account();
        AccountMainDataDto accountMainDataDto = new AccountMainDataDto();
        accountMainDataDto.setId(1L);

        when(accountDao.findById(anyLong())).thenReturn(account);
        when(accountMapper.toAccountMainDataDto(account)).thenReturn(accountMainDataDto);

        accountMainDataDto = accountService.findAccountMainDataDtoById(1L);

        assertEquals(Long.valueOf(1), accountMainDataDto.getId());
        verify(accountDao).findById(1L);
        verify(accountMapper).toAccountMainDataDto(account);
    }

    @Test
    public void testFindAllAccountMainDataDto() {
        List<Account> accounts = new ArrayList<>();
        List<AccountMainDataDto> accountMainDataDtoList = new ArrayList<>();

        when(accountDao.getTotalCount()).thenReturn(10L);
        when(accountDao.findAll(0, 10)).thenReturn(accounts);
        when(accountMapper.toAccountMainDataDtoList(accounts)).thenReturn(accountMainDataDtoList);

        List<AccountMainDataDto> result = accountService.findAllAccountMainDataDto(1L);

        assertEquals(result, accountMainDataDtoList);
        verify(accountDao).getTotalCount();
        verify(accountDao).findAll(0, 10);
        verify(accountMapper).toAccountMainDataDtoList(accounts);
    }

    @Test
    public void testUpdateData() {
        UpdateAccountDataDto accountUpdateDto = new UpdateAccountDataDto("test", "pass");
        Account account = new Account();
        account.setLoginDetails(new LoginDetails());
        account.setId(1L);
        account.setRole(new Role(3L, RoleTitle.USER));

        when(accountDao.findById(anyLong())).thenReturn(account);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        accountService.updateData(1L, accountUpdateDto, new AccountDetails(account));

        assertEquals("test", account.getNickname());
        assertEquals("encodedPassword", account.getLoginDetails().getPassword());
        verify(accountDao).findById(1L);
        verify(passwordEncoder).encode("pass");
        verify(accountDao).update(account);
    }

    @Test
    public void testUpdateRole() {
        UpdateAccountRoleDto accountUpdateDto = new UpdateAccountRoleDto(2L);
        Account account = ObjectCreator.createAccount();
        Role role = new Role(2L, RoleTitle.ADMINISTRATOR);

        when(accountDao.findById(anyLong())).thenReturn(account);
        when(roleDao.findById(2L)).thenReturn(role);

        accountService.updateRole(1L, accountUpdateDto, new AccountDetails(ObjectCreator.createOwnerAccount()));

        assertEquals(role, account.getRole());
        verify(accountDao).findById(1L);
        verify(roleDao).findById(2L);
        verify(accountDao).update(account);
    }

    @Test
    public void testUpdateRoleToOwner() {
        UpdateAccountRoleDto accountUpdateDto = new UpdateAccountRoleDto(1L);
        Account userAccount = ObjectCreator.createAccount();
        Account ownerAccount = ObjectCreator.createOwnerAccount();
        Role administratorRole = new Role(2L, RoleTitle.ADMINISTRATOR);
        Role ownerRole = new Role(1L, RoleTitle.OWNER);

        when(accountDao.findById(anyLong())).thenReturn(userAccount);
        when(roleDao.findById(2L)).thenReturn(administratorRole);
        when(roleDao.findById(1L)).thenReturn(ownerRole);
        when(accountDao.findByRole(RoleTitle.OWNER)).thenReturn(ownerAccount);

        accountService.updateRole(1L, accountUpdateDto, new AccountDetails(ownerAccount));

        assertEquals(ownerRole, userAccount.getRole());
        assertEquals(administratorRole, ownerAccount.getRole());
        verify(accountDao).findById(1L);
        verify(roleDao).findById(1L);
        verify(accountDao).findByRole(RoleTitle.OWNER);
        verify(roleDao).findById(2L);
        verify(accountDao).update(ownerAccount);
        verify(accountDao).update(userAccount);
    }

    @Test
    public void testUpdateOwnerRole() {
        UpdateAccountRoleDto accountUpdateDto = new UpdateAccountRoleDto(2L);
        Account account = ObjectCreator.createOwnerAccount();

        when(accountDao.findById(anyLong())).thenReturn(account);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            accountService.updateRole(1L, accountUpdateDto, new AccountDetails(account));
        });

        assertEquals("You can't change the \"OWNER\" role", dataChangesException.getMessage());
        verify(accountDao).findById(1L);
    }

    @Test
    public void testDeleteById() {
        Account account = ObjectCreator.createAccount();

        accountService.deleteById(1L, new AccountDetails(account));

        verify(accountDao).deleteById(1L);
    }

    @Test
    public void testAddSavedAlbum() {
        Account account = ObjectCreator.createAccount();
        Album album = new Album();

        when(accountDao.findWithSavedAlbumsById(anyLong())).thenReturn(account);
        when(albumDao.findById(anyLong())).thenReturn(album);

        accountService.addSavedAlbum(1L, 1L, new AccountDetails(account));

        assertEquals(1L, account.getSavedAlbums().size());
        verify(accountDao).findWithSavedAlbumsById(1L);
        verify(albumDao).findById(1L);
    }

    @Test
    public void testAddSavedAlbumException() {
        Account account = ObjectCreator.createAccount();
        Album album = new Album();
        account.getSavedAlbums().add(album);

        when(accountDao.findWithSavedAlbumsById(anyLong())).thenReturn(account);
        when(albumDao.findById(anyLong())).thenReturn(album);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            accountService.addSavedAlbum(1L, 1L, new AccountDetails(account));
        });

        assertEquals("Album has already been saved", dataChangesException.getMessage());
        verify(accountDao).findWithSavedAlbumsById(1L);
        verify(albumDao).findById(1L);
    }

    @Test
    public void testRemoveSavedAlbum() {
        Account account = ObjectCreator.createAccount();
        Album album = new Album();
        account.getSavedAlbums().add(album);

        when(accountDao.findWithSavedAlbumsById(anyLong())).thenReturn(account);
        when(albumDao.findById(anyLong())).thenReturn(album);

        accountService.removeSavedAlbum(1L, 1L, new AccountDetails(account));

        assertEquals(0L, account.getSavedAlbums().size());
        verify(accountDao).findWithSavedAlbumsById(1L);
        verify(albumDao).findById(1L);
    }

    @Test
    public void testRemoveSavedAlbumException() {
        Account account = ObjectCreator.createAccount();
        Album album = new Album();

        when(accountDao.findWithSavedAlbumsById(anyLong())).thenReturn(account);
        when(albumDao.findById(anyLong())).thenReturn(album);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            accountService.removeSavedAlbum(1L, 1L, new AccountDetails(account));
        });

        assertEquals("Album not saved", dataChangesException.getMessage());
        verify(accountDao).findWithSavedAlbumsById(1L);
        verify(albumDao).findById(1L);
    }
}
