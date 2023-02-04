package senla.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class Album extends AEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Account creator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "songs_in_albums",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songsIn;
}
