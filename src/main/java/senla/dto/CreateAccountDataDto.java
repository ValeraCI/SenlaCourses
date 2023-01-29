package senla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAccountDataDto {
    private String nickname;
    private String email;
    private String password;
}
