package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senla.annotations.Loggable;
import senla.dao.AccountDao;
import senla.models.Account;
import senla.models.AccountDetails;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountDao accountDao;

    @Loggable
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.findByEmail(username);

        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        }

        return new AccountDetails(account);
    }
}
