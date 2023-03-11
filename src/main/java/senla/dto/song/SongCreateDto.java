package senla.dto.song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongCreateDto {
    private String title;
    private Long genreId;
    private Set<Long> authorsId;
    private String albumCreator;
    private Long albumId;
}
