package senla.dto.album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumCreateUpdateDataDto {
    @Size(min = 1, max = 50,
            message = "The title should be in the range from 1 to 50")
    private String title;
    @NotEmpty(message = "The creatorId should not be empty")
    private Long creatorId;
}
