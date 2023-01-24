package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.exceptions.DeletionIsNotPossibleException;
import senla.exceptions.ObjectAlreadyExistsException;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Album;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumDao {
    private static final Logger logger = LoggerFactory.getLogger(AlbumDao.class);

    private static List<Album> albums = new ArrayList<>();

    public Album getById(long id){
        Optional<Album> OA = albums.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if(OA.isEmpty()){
            String errorMassage = "Альбом с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        return OA.get();
    }

    public void add(Album album){
        albums.add(album);
    }

    public void deleteById(long id){
        Album album = getById(id);
        if(album.getSongsIdIn().size() != 0){
            String errorMassage = "Невозможно удалить альбом, в нём есть песни";
            logger.error(errorMassage);
            throw new DeletionIsNotPossibleException(errorMassage);
        }

        albums.remove(album);
    }

    public void addSongsIdIn(long id, long songId){
        Album album = getById(id);

        if(album.getSongsIdIn().contains(songId)){
            String errorMassage = "Песня с индексом " + songId + " уже есть в альбоме";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        album.getSongsIdIn().add(songId);
    }

    public void removeSongsIdIn(long id, long songId){
        Album album = getById(id);

        if(!album.getSongsIdIn().contains(songId)){
            String errorMassage = "Песни с индексом " + songId + " нет в альбоме";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        album.getSongsIdIn().remove(songId);
    }
}
