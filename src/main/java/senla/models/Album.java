package senla.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "album-only-entity-graph"),
        @NamedEntityGraph(name = "album-songsIn-entity-graph",
                attributeNodes = {@NamedAttributeNode("songsIn")})
})
@Table(name = "albums")
public class Album extends AEntity{
    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "create_date")
    private Date createDate;

    @Setter
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
