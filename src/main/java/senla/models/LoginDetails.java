package senla.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "login_details")
public class LoginDetails {
    @Id
    @Column(name="account_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "password")
    private String password;

    public LoginDetails(Account account, String email, String password) {
        this.id = account.getId();
        this.account = account;
        this.email = email;
        this.password = password;
    }
}
