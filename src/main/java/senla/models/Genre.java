package senla.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "genres")
public class Genre extends AEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private GenreTitle genreTitle;
}
