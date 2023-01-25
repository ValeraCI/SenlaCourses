package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.exceptions.ObjectAlreadyExistsException;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Album;
import senla.models.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongDao{
    private static final Logger logger = LoggerFactory.getLogger(SongDao.class);

    private static final List<Song> songs = new ArrayList<>();

    public Optional<Song> getById(long id){
        return songs.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    private Song getSongById(long id){
        Optional<Song> accountOptional = getById(id);
        if(accountOptional.isEmpty()){
            String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }
        return accountOptional.get();
    }

    public void add(Song song){
        songs.add(song);
    }

    public void delete(long id){
        Song song = getSongById(id);
        songs.remove(song);
    }

    public void addContainedIn(long id, Album album){
        Song song = getSongById(id);

        if(song.getContainedIn().contains(album)) {
            String errorMassage = "Песня с индексом" + song.getId() + " уже есть в альбоме с индексом " + album.getId();
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        song.getContainedIn().add(album);
    }

    public void removeContainedIn(long id, Album album){
        Song song = getSongById(id);

        if(!song.getContainedIn().contains(album))
        {
            String errorMassage = "Песни с индексом" + song.getId() + " нет в альбоме с индексом " + album.getId();
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        song.getContainedIn().remove(album);
    }
}
