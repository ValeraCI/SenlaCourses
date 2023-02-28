package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senla.annotations.Loggable;
import senla.dto.AuthRequest;
import senla.services.api.AuthenticationService;

@RestController
@RequestMapping(value = "/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Loggable
    @GetMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest request) {
        return authenticationService.login(request);
    }
}
