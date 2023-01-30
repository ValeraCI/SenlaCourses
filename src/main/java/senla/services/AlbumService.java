package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.AlbumDao;
import senla.dto.AlbumDto;
import senla.models.Album;

@Service
public class AlbumService {

    private AlbumDao albumDao;

    @Autowired
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void add(AlbumDto albumDto){

    }

    public Album getAlbumById(long id) {
       return albumDao.getAlbumById(id);
    }

    public AlbumDto getAlbumDtoById(long id) {
        Album album = getAlbumById(id);
        AlbumDto albumDto = new AlbumDto(id, album.getTitle(), album.getSongsIn().size(), album.getCreator().getId());
        return albumDto;
    }

    public void deleteById(long id){
        albumDao.delete(id);
    }
}
