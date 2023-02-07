package senla.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToOne(mappedBy = "song", fetch = FetchType.LAZY)
    private Location location;

}
