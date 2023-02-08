package senla.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import senla.models.RoleTitle;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithLoginDetailsDto {
    private long id;
    private String nickname;
    private RoleTitle role;
    private String email;
    private String password;
}
