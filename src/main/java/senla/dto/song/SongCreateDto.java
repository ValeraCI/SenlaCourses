package senla.dto.song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongCreateDto {

    @Size(min = 1, max = 50,
            message = "The title should be in the range from 1 to 50")
    private String title;
    @NotEmpty(message = "The genreId should not be empty")
    private Long genreId;
    @NotEmpty(message = "The authorsId should not be empty")
    private Set<Long> authorsId;
    @NotEmpty(message = "The albumCreator should not be empty")
    private String albumCreator;

    @NotEmpty(message = "The albumId should not be empty")
    private Long albumId;
}
