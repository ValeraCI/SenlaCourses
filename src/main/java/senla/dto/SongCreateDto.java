package senla.dto;

import senla.util.Genre;

import java.util.List;

public class SongCreateDto {
    private long id;
    private String title;
    private Genre genre;
    private List<Long> authorsId;
    private String albumCreator;
    private Long albumId;

    public SongCreateDto(){}

    public SongCreateDto(long id, String title, Genre genre, List<Long> authorsId, String albumCreator, Long albumId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorsId = authorsId;
        this.albumCreator = albumCreator;
        this.albumId = albumId;
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

    public String getAlbumCreator() {
        return albumCreator;
    }

    public void setAlbumCreator(String albumCreator) {
        this.albumCreator = albumCreator;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
