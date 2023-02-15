package senla.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.LoginDetailsDao;
import senla.dao.RoleDao;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.models.Account;
import senla.models.LoginDetails;
import senla.models.Role;
import senla.models.RoleTitle;
import senla.services.AccountService;
import senla.services.AccountServiceImpl;
import senla.test.configuration.Application;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;

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
    @Mock
    private RoleDao roleDao;

    private AccountService accountService;

    public AccountServiceImplTest(){
        MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(accountDao, albumDao, loginDetailsDao, roleDao);
    }

    private Role createRole(){
        Role role = new Role();
        role.setRoleTitle(RoleTitle.USER);
        role.setId(3L);
        return role;
    }

    private Account createAccount(){
        Account account = new Account();

        account.setNickname("Tester");
        account.setRegistrationDate(LocalDate.now());
        account.setLoginDetails(new LoginDetails(account, "test@mail.ru", "1234"));
        account.setRole(createRole());
        return account;
    }

    @Test
    public void findAccountWithLoginDetailsDtoByEmailTest(){
        given(accountDao.findByEmail("test@mail.ru"))
                .willReturn(createAccount());

        AccountWithLoginDetailsDto account =
                accountService.findAccountWithLoginDetailsDtoByEmail("test@mail.ru");

        Assert.assertEquals("USER", account.getRole().toString());
    }
}
