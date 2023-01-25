package senla.models;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Song {
    @NonNull
    private final long id;
    @NonNull
    private final String title;
    @NonNull
    private final Genre genre;
    @NonNull
    private final List<Account> authors;
    @Setter
    private Location location;
    @NonNull
    private final List<Album> containedIn;

}
