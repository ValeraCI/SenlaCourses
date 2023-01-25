package senla.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import senla.util.Role;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class Account {
    private final long id;
    @Setter
    private String nickname;
    private final Date registrationDate;
    @Setter
    private Role role;

    private final List<Song> songsPerformed;
    private final List<Album> createdAlbums;
    private final List<Album> savedAlbums;

}