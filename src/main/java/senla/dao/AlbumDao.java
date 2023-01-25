package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.exceptions.DeletionIsNotPossibleException;
import senla.exceptions.ObjectAlreadyExistsException;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Album;
import senla.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumDao {
    private static final Logger logger = LoggerFactory.getLogger(AlbumDao.class);

    private static List<Album> albums = new ArrayList<>();

    public Optional<Album> getById(long id){
        return albums.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    private Album getAlbumById(long id){
        Optional<Album> accountOptional = getById(id);
        if(accountOptional.isEmpty()){
            String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }
        return accountOptional.get();
    }

    public void add(Album album){
        albums.add(album);
    }

    public void delete(long id){
        Album album = getAlbumById(id);

        if(album.getSongsIn().size() != 0){
            String errorMassage = "Невозможно удалить альбом, в нём есть песни";
            logger.error(errorMassage);
            throw new DeletionIsNotPossibleException(errorMassage);
        }
        albums.remove(album);
    }

    public void addSongsIdIn(long id, Song song){
        Album album = getAlbumById(id);

        if(album.getSongsIn().contains(song)){
            String errorMassage = "Песня с индексом " + song.getId() + " уже есть в альбоме";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        album.getSongsIn().add(song);
    }

    public void removeSongsIdIn(long id, Song song){
        Album album = getAlbumById(id);

        if(!album.getSongsIn().contains(song)){
            String errorMassage = "Песни с индексом " + song.getId() + " нет в альбоме";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        album.getSongsIn().remove(song);
    }
}
