package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dto.AlbumDto;
import senla.models.Album;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Service
public class AlbumService {
    private AlbumDao albumDao;
    private AccountDao accountDao;

    @Autowired
    public AlbumService(AlbumDao albumDao, AccountDao accountDao) {
        this.albumDao = albumDao;
        this.accountDao = accountDao;
    }

    public void add(AlbumDto albumDto){
        Album album = new Album(albumDto.getId(), albumDto.getTitle(), new Date(),
                new ArrayList<>(), albumDto.getCreatorId());
        accountDao.addCreatedAlbumId(albumDto.getCreatorId(), albumDto.getId());
        albumDao.add(album);
    }

    public AlbumDto getById(long id) {
        Album album = albumDao.getById(id);
        AlbumDto albumDto = new AlbumDto(id, album.getTitle(), album.getSongsIdIn().size(), album.getCreatorId());
        return albumDto;
    }

    public List<Long> getSongsInById(long id) {
        Album album = albumDao.getById(id);
        return album.getSongsIdIn();
    }

    public void deleteById(long id){
        Album album = albumDao.getById(id);
        accountDao.removeCreatedAlbumId(album.getCreatorId(), id);
        albumDao.deleteById(id);
    }

    public void addSongIdIn(long id, long songId){
        albumDao.addSongsIdIn(id, songId);
    }

    public void removeSongIdIn(long id, long songId){
        albumDao.removeSongsIdIn(id,songId);
    }

}
