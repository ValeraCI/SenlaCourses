package senla.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Genre extends AEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private GenreTitle genreTitle;
}
