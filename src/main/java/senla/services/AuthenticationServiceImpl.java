package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import senla.dto.AuthRequest;
import senla.services.api.AuthenticationService;
import senla.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService accountDetailsService;

    @Override
    public ResponseEntity login(AuthRequest request) {
        try {
            String username = request.getEmail();
            String password = request.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails account = accountDetailsService.loadUserByUsername(username);

            if (Objects.isNull(account)) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtUtil.create(account);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
