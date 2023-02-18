package senla.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "roles")
@Getter
@Setter
public class Role extends AEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private RoleTitle roleTitle;

    public Role(Long id, RoleTitle roleTitle) {
        super(id);
        this.roleTitle = roleTitle;
    }

    public Role(Long id) {
        super(id);
    }
}
