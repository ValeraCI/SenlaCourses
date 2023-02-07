package senla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.Account;
import senla.models.Genre;
import senla.models.Location;
import senla.models.Song;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SongService{
    private SongDao songDao;
    private AlbumDao albumDao;
    private AccountDao accountDao;
    private GenreDao genreDao;

    private String createLocation(SongCreateDto songCreateDto){
        StringBuilder sb = new StringBuilder(".\\music\\");
        sb.append(songCreateDto.getAlbumCreator());
        sb.append("\\");
        sb.append(albumDao.findById(songCreateDto.getAlbumId()).getTitle());
        sb.append("\\");
        sb.append(songCreateDto.getTitle());
        sb.append(".mp3");

        return sb.toString();
    }

    @Transactional
    public void save(SongCreateDto songCreateDto){
        Song song = new Song();
        List<Account> authors = new ArrayList<>();

        for (Long authorId: songCreateDto.getAuthorsId()){
            authors.add(accountDao.findById(authorId));
        }

        song.setAuthors(authors);
        song.setTitle(songCreateDto.getTitle());
        Genre genre = genreDao.findById(songCreateDto.getGenreId());
        song.setGenre(genre);

        song.setLocation(new Location(song, createLocation(songCreateDto)));

        songDao.save(song);
    }

    @Transactional
    public void deleteById(Long id){
        songDao.deleteById(id);
    }

    @Transactional
    public SongInfoDto findSongInfoDtoById(Long id) {
        SongInfoDto songInfoDto = new SongInfoDto();

        Song song = songDao.findById(id);
        songInfoDto.setId(id);
        songInfoDto.setTitle(song.getTitle());
        List<String> authorsNicknames = new ArrayList<>();
        for (Account author: song.getAuthors()) {
            authorsNicknames.add(author.getNickname());
        }

        songInfoDto.setAuthorsNicknames(authorsNicknames);

        return songInfoDto;
    }
}
