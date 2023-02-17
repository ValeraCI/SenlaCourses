package senla.test.dao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.LoginDetailsDao;
import senla.dao.RoleDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Account;
import senla.models.LoginDetails;
import senla.test.configuration.Application;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class AccountAndLoginDetailsDaoTest {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LoginDetailsDao loginDetailsDao;
    @Autowired
    private RoleDao roleDao;

    private Account createAccount(){
        Account account = new Account();
        account.setNickname("Tester");
        account.setRegistrationDate(LocalDate.now());
        account.setLoginDetails(new LoginDetails(account, "test@mail.ru", "1234"));
        account.setRole(roleDao.findById(3L));
        return account;
    }

    @Test
    public void findByIdTest(){
        Account account = accountDao.findById(1L);

        Assert.assertEquals("Valerix", account.getNickname());
        Assert.assertEquals(1L, account.getRole().getId().longValue());
    }

    @Test
    public void findByEmailTest(){
        Account account = accountDao.findByEmail("cidikvalera@gmail.com");

        Assert.assertEquals("cidikvalera@gmail.com", account.getLoginDetails().getEmail());
        Assert.assertEquals("Valerix", account.getNickname());
        Assert.assertEquals(1L, account.getRole().getId().longValue());
    }

    @Test
    public void findAllTest(){
       List<Account> accounts = accountDao.findAll();

       for(int i = 0; i < accounts.size(); i++){
          Assert.assertEquals(accounts.get(i).getNickname(), accountDao.findById(i+1L).getNickname());
       }
    }

    @Test
    public void saveTest(){
        Account account = createAccount();

        Long index = accountDao.save(account);
        account = accountDao.findById(index);

        Assert.assertEquals("test@mail.ru", account.getLoginDetails().getEmail());
        Assert.assertEquals("Tester", account.getNickname());
        Assert.assertEquals(3L, account.getRole().getId().longValue());
    }

    @Test
    public void updateTest(){
        Account account = accountDao.findById(2L);
        Assert.assertEquals("MaJIeHkuu_Ho_BeJIuKuu", account.getNickname());

        account.setNickname("MaJIeHkuu_u_He_BeJIuKuu");
        accountDao.update(account);

        account = accountDao.findById(2L);
        Assert.assertEquals("MaJIeHkuu_u_He_BeJIuKuu", account.getNickname());
    }

    @Test
    public void updatePasswordTest(){
        Account account = accountDao.findById(1L);
        account.getLoginDetails().setPassword("123456");
        loginDetailsDao.update(account.getLoginDetails());

        account = accountDao.findById(1L);

        Assert.assertEquals("cidikvalera@gmail.com", account.getLoginDetails().getEmail());
        Assert.assertEquals("Valerix", account.getNickname());
        Assert.assertEquals(1, account.getRole().getId().longValue());
        Assert.assertEquals("123456", account.getLoginDetails().getPassword());
    }

    @Test(expected = DataBaseWorkException.class)
    public void deleteByIdTest(){
        accountDao.deleteById(3L);
        Account account = accountDao.findById(3L);
    }
}
