package senla.models;

import senla.util.Genre;

import java.util.List;

public class Song {
    private final long id;
    private final String title;
    private final Genre genre;

    private final List<Long> authorsId;
    private final List<Long> containedInId;

    private final String path;

    public Song(long id, String title, Genre genre, List<Long> authorsId, List<Long> containedInId, String path) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorsId = authorsId;
        this.containedInId = containedInId;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Long> getAuthorsId() {
        return authorsId;
    }

    public List<Long> getContainedInId() {
        return containedInId;
    }

    public String getPath() {
        return path;
    }
}
