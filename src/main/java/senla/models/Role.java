package senla.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "roles")
@Getter
public class Role extends AEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private RoleTitle roleTitle;
}
