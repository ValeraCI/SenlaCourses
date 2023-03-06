package senla.services.api;

import org.springframework.http.ResponseEntity;
import senla.dto.AuthRequest;

public interface AuthenticationService {
    ResponseEntity login(AuthRequest request);
}
