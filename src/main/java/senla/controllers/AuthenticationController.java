package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import senla.annotations.Loggable;
import senla.dto.AuthRequest;
import senla.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    //private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailsService accountDetailsService;

    //TODO настроить сервис выдачи токена, настроить доступ по ролям, регистрацию, проверку пороля

    @Loggable
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest request) {
        try {
            System.out.println("Email: "+ request.getEmail());

            String username = request.getEmail();
            String password = request.getPassword();

            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

            UserDetails account = accountDetailsService.loadUserByUsername(username);

            //Тут должна быть проверка пароля(не забыть про хеширование пароля)

            String token = jwtUtil.create(account);

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);



            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
