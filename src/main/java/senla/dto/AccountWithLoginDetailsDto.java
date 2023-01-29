package senla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import senla.models.Role;

@Getter
@Setter
@AllArgsConstructor
public class AccountWithLoginDetailsDto {
    private long id;
    private String nickname;
    private Role role;
    private String email;
    private String password;
}
