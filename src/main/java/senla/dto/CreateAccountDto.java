package senla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@AllArgsConstructor
@Getter
public class CreateAccountDto {
    private final String nickname;
    private final Date registrationDate;
}
