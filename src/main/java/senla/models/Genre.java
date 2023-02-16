package senla.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "genres")
public class Genre extends AEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private GenreTitle genreTitle;
}
