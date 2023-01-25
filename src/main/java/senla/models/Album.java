package senla.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Album {
    private final long id;
    private String title;
    private final Date createDate;
    private final List<Song> songsIn;
    private final Account creator;
    private final List<Account> savedFrom;
}
