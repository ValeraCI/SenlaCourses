package senla.models;

import java.util.List;
import java.util.Date;

public class Album {
    private final long id;
    private final String title;
    private final Date createDate;
    private final List<Long> songsIdIn;
    private final Long creatorId;

    public Album(long id, String title, Date createDate, List<Long> songsIdIn, Long creatorId) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.songsIdIn = songsIdIn;
        this.creatorId = creatorId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public List<Long> getSongsIdIn() {
        return songsIdIn;
    }

    public Long getCreatorId() {
        return creatorId;
    }

}
