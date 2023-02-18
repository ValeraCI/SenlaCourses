package senla.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends AEntity{
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private LoginDetails loginDetails;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "authors",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> songsPerformed;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private Set<Album> createdAlbums;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "saved_albums",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Album> savedAlbums;

}