package senla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UpdateAccountDto {
    private long id;
    private String nickname;
    private int roleId;
}
