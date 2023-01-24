package senla.models;

import senla.util.Role;
import java.util.Date;
import java.util.List;

public class Account {
    private final long id;
    private String nickname;
    private final Date registrationDate;
    private Role role;

    private final List<Long> songsIdPerformed;
    private final List<Long> createdAlbumsId;
    private final List<Long> savedAlbumsId;

    public Account(long id, String nickname, Date registrationDate, Role role, List<Long> songsIdPerformed,
                   List<Long> createdAlbumsId, List<Long> savedAlbumsId) {
        this.id = id;
        this.nickname = nickname;
        this.registrationDate = registrationDate;
        this.role = role;
        this.songsIdPerformed = songsIdPerformed;
        this.createdAlbumsId = createdAlbumsId;
        this.savedAlbumsId = savedAlbumsId;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getSongsIdPerformed() {
        return songsIdPerformed;
    }

    public List<Long> getCreatedAlbumsId() {
        return createdAlbumsId;
    }

    public List<Long> getSavedAlbumsId() {
        return savedAlbumsId;
    }
}