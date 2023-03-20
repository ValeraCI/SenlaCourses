package senla.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.configuration.WebMvcConfig;
import senla.dto.AuthRequest;
import senla.services.AuthenticationServiceImpl;
import senla.util.JwtUtil;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class AuthenticationServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private UserDetailsService accountDetailsService;
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    public AuthenticationServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin() {
        AuthRequest request = new AuthRequest("testEmail", "testPassword");
        String username = request.getEmail();
        String password = request.getPassword();
        UserDetails userDetails = User.builder()
                .username(username)
                .password(password)
                .roles("testRole")
                .build();

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(accountDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtUtil.create(userDetails)).thenReturn("token");

        ResponseEntity responseEntity = authenticationService.login(request);

        Map<Object, Object> response = (Map<Object, Object>) responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(username, response.get("username"));
        assertEquals("token", response.get("token"));
    }

    @Test
    public void testLoginException() {
        AuthRequest request = new AuthRequest("testEmail", "testPassword");

        BadCredentialsException badCredentialsException =
                assertThrows(BadCredentialsException.class, () -> {
                    authenticationService.login(request);
                });

        assertEquals("Invalid username or password", badCredentialsException.getMessage());
    }
}
