package senla.test.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.LoginDetailsDao;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.exceptions.DataChangesException;
import senla.models.*;
import senla.services.AccountServiceImpl;
import senla.services.api.AccountService;
import senla.test.configuration.Application;
import senla.util.mappers.AccountMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class AccountServiceImplTest {

    @Mock
    private AccountDao accountDao;
    @Mock
    private AlbumDao albumDao;
    @Mock
    private LoginDetailsDao loginDetailsDao;

    @Autowired
    private AccountMapper accountMapper;

    private AccountService accountService;

    public AccountServiceImplTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void beforeEach(){
        this.accountService = new AccountServiceImpl(accountDao, albumDao, loginDetailsDao, accountMapper);
    }

    private Role createRole(){
        Role role = new Role();
        role.setRoleTitle(RoleTitle.USER);
        role.setId(3L);
        return role;
    }

    private Account createAccount(){
        Account account = new Account();

        account.setId(1L);
        account.setNickname("Tester");
        account.setRegistrationDate(LocalDate.now());
        account.setLoginDetails(new LoginDetails(account, "test@mail.ru", "1234"));
        account.setRole(createRole());
        account.setSavedAlbums(new HashSet<>());
        return account;
    }

    private Album createAlbum(Account account){
        Album album = new Album();

        album.setId(1L);
        album.setTitle("TestAlbum");
        album.setCreateDate(LocalDate.now());
        album.setCreator(account);

        return album;
    }

    @Test
    public void findAccountWithLoginDetailsDtoByEmailTest(){
        given(accountDao.findByEmail("test@mail.ru"))
                .willReturn(createAccount());

        AccountWithLoginDetailsDto account =
                accountService.findAccountWithLoginDetailsDtoByEmail("test@mail.ru");

        Assert.assertEquals("USER", account.getRole().toString());
        Assert.assertEquals("Tester", account.getNickname());
    }

    @Test
    public void findAllAccountMainDataDtoTest(){
        given(accountDao.findAll())
                .willReturn(new ArrayList(Arrays.asList(createAccount())));

        List<AccountMainDataDto> list = accountService.findAllAccountMainDataDto();

        Assert.assertEquals("Tester", list.get(0).getNickname());
    }

    @Test
    public void findAccountMainDataDtoByIdTest(){
        given(accountDao.findById(1L))
                .willReturn(createAccount());

        AccountMainDataDto account = accountService.findAccountMainDataDtoById(1L);

        Assert.assertEquals("Tester", account.getNickname());
    }

    @Test
    public void addSavedAlbumNoExceptionTest(){
        Account account = createAccount();

        given(accountDao.findWithSavedAlbums(1L))
                .willReturn(account);
        given(albumDao.findById(1L))
                .willReturn(createAlbum(account));

        accountService.addSavedAlbum(1L, 1L);

        Assert.assertEquals(account.getSavedAlbums().size(), 1);
    }

    @Test(expected = DataChangesException.class)
    public void addSavedAlbumExceptionTest() {
        Account account = createAccount();

        given(accountDao.findWithSavedAlbums(1L))
                .willReturn(account);
        given(albumDao.findById(1L))
                .willReturn(createAlbum(account));


        accountService.addSavedAlbum(1L, 1L);
        accountService.addSavedAlbum(1L, 1L);
    }

    @Test
    public void removeSavedAlbumNoExceptionTest(){
        Account account = createAccount();
        Album album = createAlbum(account);

        given(accountDao.findWithSavedAlbums(1L))
                .willReturn(account);
        given(albumDao.findById(1L))
                .willReturn(album);

        account.getSavedAlbums().add(album);

        accountService.removeSavedAlbum(1L, 1L);

        Assert.assertEquals(account.getSavedAlbums().size(), 0);
    }

    @Test(expected = DataChangesException.class)
    public void removeSavedAlbumExceptionTest() {
        Account account = createAccount();
        Album album = createAlbum(account);

        given(accountDao.findWithSavedAlbums(1L))
                .willReturn(account);
        given(albumDao.findById(1L))
                .willReturn(album);

        account.getSavedAlbums().add(album);


        accountService.removeSavedAlbum(1L, 1L);
        accountService.removeSavedAlbum(1L, 1L);
    }

    @Test
    public void deleteByIdTest(){
        accountService.deleteById(1L);

        verify(accountDao).deleteById(1L);
    }

    @Test
    public void saveTest(){
        AccountDataDto accountDataDto = new AccountDataDto("Tester", "test@mail.ru", "1234");

        Assert.assertEquals(0L, accountService.save(accountDataDto).longValue());
    }
}
