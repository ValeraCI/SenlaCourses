package senla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import senla.models.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongInfoDto {
    private long id;
    private String title;
    private Genre genre;
    private List<String> authorsNicknames;
}
