package senla.dto;

public class AlbumDto {
    private long id;
    private String title;
    private int songsNum;
    private Long creatorId;

    public AlbumDto(){}

    public AlbumDto(long id, String title, int songsNum, Long creatorId) {
        this.id = id;
        this.title = title;
        this.songsNum = songsNum;
        this.creatorId = creatorId;
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

    public int getSongsNum() {
        return songsNum;
    }

    public void setSongsNum(int songsNum) {
        this.songsNum = songsNum;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
