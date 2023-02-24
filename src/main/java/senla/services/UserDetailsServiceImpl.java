package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import senla.annotations.Loggable;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.services.api.AccountService;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Loggable
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountWithLoginDetailsDto account = accountService.findAccountWithLoginDetailsDtoByEmail(username);

        if(Objects.isNull(account)){
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        }
        return User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole().toString())
                .build();

    }
}
