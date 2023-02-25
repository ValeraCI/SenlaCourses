package senla.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songs")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "song-only-entity-graph"),
        @NamedEntityGraph(name = "song-authors-entity-graph",
                attributeNodes = {@NamedAttributeNode("authors")})
})

public class Song extends AEntity{
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(mappedBy = "songsPerformed", fetch = FetchType.LAZY)
    private List<Account> authors;

    @OneToOne(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Location location;

    @ManyToMany(mappedBy = "songsIn")
    private Set<Album> containedIn;

}
