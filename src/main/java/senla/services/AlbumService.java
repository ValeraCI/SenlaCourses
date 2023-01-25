package senla.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.AlbumDao;
import senla.dto.AlbumDto;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Account;
import senla.models.Album;
import senla.models.Song;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class AlbumService {
    private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);

    private AlbumDao albumDao;
    private AccountService accountService;
    private SongService songService;

    @Autowired
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    @Autowired
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    public void add(AlbumDto albumDto){
        Account account = accountService.getAccountById(albumDto.getCreatorId());

        Album album = new Album(albumDto.getId(), albumDto.getTitle(), new Date(),
                new ArrayList<>(), account, new ArrayList<>());

        albumDao.add(album);
        accountService.addCreatedAlbum(albumDto.getCreatorId(), albumDto.getId());
    }

    public Album getAlbumById(long id) {
        Optional<Album> albumOptional = albumDao.getById(id);

        if(albumOptional.isEmpty()){
            String errorMassage = "Альбом с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        return  albumOptional.get();
    }

    public AlbumDto getAlbumDtoById(long id) {
        Album album = getAlbumById(id);
        AlbumDto albumDto = new AlbumDto(id, album.getTitle(), album.getSongsIn().size(), album.getCreator().getId());
        return albumDto;
    }

    public List<Song> getSongsInById(long id) {
        Album album = getAlbumById(id);
        return album.getSongsIn();
    }

    public void deleteById(long id){
        Album album = getAlbumById(id);

        accountService.removeCreatedAlbum(album.getCreator().getId(), id);
        albumDao.delete(id);
    }

    public void addSongIdIn(long id, long songId){
        Song song = songService.getSongById(songId);

        albumDao.addSongsIdIn(id, song);
    }

    public void removeSongIdIn(long id, long songId){
        Song song = songService.getSongById(songId);

        albumDao.removeSongsIdIn(id, song);
    }
}
