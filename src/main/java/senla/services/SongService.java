package senla.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.SongDao;
import senla.dto.SongCreateDto;
import senla.dto.SongInfoDto;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Account;
import senla.models.Album;
import senla.models.Location;
import senla.models.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService{
    private static final Logger logger = LoggerFactory.getLogger(SongService.class);

    private SongDao songDao;
    private AlbumService albumService;
    private AccountService accountService;

    @Autowired
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }
    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private Location createLocation(Song song, SongCreateDto songCreateDto){
        StringBuilder sb = new StringBuilder(".\\music\\");
        sb.append(songCreateDto.getAlbumCreator());
        sb.append("\\");
        sb.append(albumService.getAlbumById(songCreateDto.getAlbumId()).getTitle());
        sb.append("\\");
        sb.append(songCreateDto.getTitle());
        sb.append(".mp3");

        return new Location(song, sb.toString());
    }

    public void add(SongCreateDto songCreateDto){

        List<Account> authors = new ArrayList<>();
        for (long authorId:songCreateDto.getAuthorsId()) {
            authors.add(accountService.getAccountById(authorId));
        }

        Song song = new Song(songCreateDto.getId(), songCreateDto.getTitle(), songCreateDto.getGenre(),
                authors, new ArrayList(List.of(songCreateDto.getAlbumId())));
        Location location = createLocation(song, songCreateDto);
        song.setLocation(location);
        songDao.add(song);
    }

    public Song getSongById(long id) {
        Optional<Song> optionalSong = songDao.getById(id);
        if(optionalSong.isEmpty()){
            String errorMassage = "Песня с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }
        return optionalSong.get();
    }

    public SongInfoDto getSongInfoDtoById(long id) {
        Song song = getSongById(id);

        List<String> authorsNicknames = new ArrayList<>();

        for(Account account: song.getAuthors()){
            authorsNicknames.add(account.getNickname());
        }

        SongInfoDto songInfoDto = new SongInfoDto(id, song.getTitle(), song.getGenre(), authorsNicknames);
        return songInfoDto;
    }

    public void deleteById(long id){;
        songDao.delete(id);
    }

    public void addContainedInId(long id, long albumId){
        Album album = albumService.getAlbumById(albumId);

        songDao.addContainedIn(id, album);
    }

    public void removeContainedInId(long id, long albumId){
        Album album = albumService.getAlbumById(albumId);

       songDao.removeContainedIn(id, album);
    }
}
