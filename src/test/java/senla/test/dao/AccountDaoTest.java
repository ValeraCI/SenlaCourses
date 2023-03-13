package senla.test.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.WebMvcConfig;
import senla.dao.AccountDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Account;
import senla.models.LoginDetails;
import senla.models.Role;
import senla.models.RoleTitle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
@Transactional
public class AccountDaoTest {
    @Autowired
    private AccountDao accountDao;
    @PersistenceContext
    protected EntityManager entityManager;

    private Account createAccount() {
        Account account = new Account();
        account.setNickname("Tester");
        account.setRegistrationDate(LocalDate.now());
        account.setLoginDetails(new LoginDetails(account, "test@mail.ru", "1234"));
        account.setRole(new Role(3L, RoleTitle.USER));
        return account;
    }

    @Test
    public void testFindById() {
        Account account = accountDao.findById(1L);

        assertNotNull(account);
        assertEquals("Valerix", account.getNickname());
        assertEquals(1L, account.getRole().getId().longValue());
    }

    @Test
    public void testFindByEmail() {
        Account account = accountDao.findByEmail("cidikvalera@gmail.com");

        assertNotNull(account);
        assertEquals("cidikvalera@gmail.com", account.getLoginDetails().getEmail());
        assertEquals("Valerix", account.getNickname());
        assertEquals(1L, account.getRole().getId().longValue());
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = accountDao.findAll(1, 10);

        assertNotNull(accounts);
        assertTrue(accounts.size() <= 10);
    }

    @Test
    public void testSave() {
        Account account = createAccount();

        Long index = accountDao.save(account);
        account = accountDao.findById(index);

        assertNotNull(account);
        assertEquals("test@mail.ru", account.getLoginDetails().getEmail());
        assertEquals("Tester", account.getNickname());
        assertEquals(3L, account.getRole().getId().longValue());
    }

    @Test
    public void testUpdate() {
        Account account = accountDao.findById(2L);
        assertEquals("MaJIeHkuu_Ho_BeJIuKuu", account.getNickname());

        account.setNickname("MaJIeHkuu_u_He_BeJIuKuu");
        accountDao.update(account);

        account = accountDao.findById(2L);
        assertEquals("MaJIeHkuu_u_He_BeJIuKuu", account.getNickname());
    }

    @Test
    public void testUpdatePassword() {
        Account account = accountDao.findById(1L);
        account.getLoginDetails().setPassword("123456");

        account = accountDao.findById(1L);

        assertEquals("cidikvalera@gmail.com", account.getLoginDetails().getEmail());
        assertEquals("Valerix", account.getNickname());
        assertEquals(1, account.getRole().getId().longValue());
        assertEquals("123456", account.getLoginDetails().getPassword());
    }

    @Test
    public void testDeleteById() {
        accountDao.deleteById(3L);

        DataBaseWorkException dataBaseWorkException =
                assertThrows(DataBaseWorkException.class, () -> {
                    accountDao.findById(3L);
                });

        assertEquals("No entity found for query", dataBaseWorkException.getMessage());
    }

    @Test
    public void testFindWithSavedAlbumsByIdInBetween() {
        List<Account> accounts = accountDao.findWithSavedAlbumsByIdInBetween(1L, 7L);

        for (Account account : accounts) {
            entityManager.detach(account);
            assertNotNull(account.getSavedAlbums());
            assertTrue(account.getId() >= 1);
            assertTrue(account.getId() < 7);
        }
    }

    @Test
    void testFindByIds() {
        Set<Long> accountIds = Set.of(1L, 2L, 3L);

        List<Account> accounts = accountDao.findByIds(accountIds);

        assertNotNull(accountIds);
        assertEquals(3, accounts.size());
    }

    @Test
    public void testGetTotalCount() {
        Long num = accountDao.getTotalCount();
        assertNotNull(num);
    }
}
