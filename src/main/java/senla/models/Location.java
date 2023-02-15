package senla.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name="song_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @Setter
    @Column(name = "path")
    private String path;

    public Location(Song song, String path) {
        this.id = song.getId();
        this.song = song;
        this.path = path;
    }
}
