package senla.models;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Song {
    private final long id;
    private final String title;
    private final Genre genre;
    private final List<Account> authors;
    @Setter
    private Location location;

}
