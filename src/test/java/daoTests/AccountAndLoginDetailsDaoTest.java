package daoTests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.Application;
import senla.dao.AccountDao;
import senla.dao.LoginDetailsDao;
import senla.dao.RoleDao;
import senla.models.Account;
import senla.models.Album;
import senla.models.LoginDetails;

import java.util.Date;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class AccountAndLoginDetailsDaoTest {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LoginDetailsDao loginDetailsDao;
    @Autowired
    private RoleDao roleDao;

    private Account createAccount(){
        Account account = new Account();

        account.setNickname("Tester7899877");
        account.setRegistrationDate(new Date());
        account.setLoginDetails(new LoginDetails(account, "test@mail.ru", "1234"));
        account.setRole(roleDao.findById(3L));
        return account;
    }

    @Test
    public void CRUDMethods(){
        Account account = createAccount();

        accountDao.save(account);
        loginDetailsDao.save(account.getLoginDetails());

        Account daoAccount = accountDao.findByEmail(account.getLoginDetails().getEmail());
        Assert.assertEquals(account.getNickname(), daoAccount.getNickname());
        daoAccount.setNickname("Tester7899877");
        accountDao.update(daoAccount);
        daoAccount = accountDao.findByEmail(account.getLoginDetails().getEmail());
        Assert.assertEquals("Tester7899877", daoAccount.getNickname());

        accountDao.deleteById(daoAccount.getId());
    }

    @Test
    public void updatePassword(){
        Account account = accountDao.findById(1L);
        account.getLoginDetails().setPassword("123456");
        loginDetailsDao.update(account.getLoginDetails());

        LoginDetails daoLoginDetails = accountDao.findById(account.getId()).getLoginDetails();

        Assert.assertEquals("123456", daoLoginDetails.getPassword());
    }

    @Test
    public void operationsWithSavedAlbums(){
        Set<Album> savedAlbums = accountDao.findSavedAlbumsById(1L);
        int albumsSize = savedAlbums.size();
        Album album = savedAlbums.stream().findFirst().get();

        accountDao.removeSavedAlbum(1L, album);

        Assert.assertEquals(albumsSize-1, accountDao.findSavedAlbumsById(1L).size());

        accountDao.addSavedAlbum(1L, album);

        Assert.assertEquals(albumsSize, accountDao.findSavedAlbumsById(1L).size());
    }

    @Test
    public void getCreatedAlbums(){
        Set<Album> createdAlbums = accountDao.findCreatedAlbumsById(1L);
        createdAlbums.stream().forEach(a -> System.out.println(a.getTitle()));
    }
}
