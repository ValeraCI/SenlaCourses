package senla.dto.song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import senla.models.GenreTitle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongInfoDto {
    private long id;
    private String title;
    private List<String> authorsNicknames;
}