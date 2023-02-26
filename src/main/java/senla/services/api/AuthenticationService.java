package senla.services.api;

import org.springframework.http.ResponseEntity;
import senla.dto.AuthRequest;

public interface AuthenticationService {
    public ResponseEntity login(AuthRequest request);
}
