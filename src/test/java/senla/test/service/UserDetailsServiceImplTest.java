package senla.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.configuration.WebMvcConfig;
import senla.dao.AccountDao;
import senla.models.Account;
import senla.models.LoginDetails;
import senla.models.Role;
import senla.models.RoleTitle;
import senla.services.UserDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class UserDetailsServiceImplTest {

    @Mock
    private AccountDao accountDao;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public UserDetailsServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        Account account = new Account();
        account.setNickname("test");
        account.setLoginDetails(new LoginDetails(account, "test", "test"));
        account.setRole(new Role(1L, RoleTitle.OWNER));

        when(accountDao.findByEmail(anyString())).thenReturn(account);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("test");

        assertEquals("test", userDetails.getUsername());
        assertEquals("test", userDetails.getUsername());
        verify(accountDao).findByEmail("test");
    }

    @Test
    public void testLoadUserByUsernameException() {
        when(accountDao.findByEmail(anyString())).thenReturn(null);

        UsernameNotFoundException usernameNotFoundException =
                assertThrows(UsernameNotFoundException.class, () -> {
                    userDetailsServiceImpl.loadUserByUsername("test");
                });

        assertEquals("User with email: test not found", usernameNotFoundException.getMessage());
        verify(accountDao).findByEmail("test");
    }
}