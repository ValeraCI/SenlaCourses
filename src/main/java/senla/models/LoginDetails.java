package senla.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LoginDetails {
    private final Account account;
    private final String email;
    @Setter
    private String password;
}
