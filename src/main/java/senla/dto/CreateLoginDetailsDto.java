package senla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateLoginDetailsDto {
    private final long accountId;
    private final String email;
    private final String password;
}
