package senla.models;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public class AEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
}
