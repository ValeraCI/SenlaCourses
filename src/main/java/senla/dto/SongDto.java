package senla.dto;

import senla.util.Genre;

import java.util.List;

public class SongDto {
    private long id;
    private String title;
    private Genre genre;
    private List<Long> authorsId;

    public SongDto(){}

    public SongDto(long id, String title, Genre genre, List<Long> authorsId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorsId = authorsId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Long> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(List<Long> authorsId) {
        this.authorsId = authorsId;
    }
}
