package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.exceptions.ObjectAlreadyExistsException;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongDao{
    private static final Logger logger = LoggerFactory.getLogger(SongDao.class);

    private static final List<Song> songs = new ArrayList<>();

    public Song getById(long id){
        Optional<Song> OS = songs.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if(OS.isEmpty()){
            String errorMassage = "Песня с индексом " + id + " не обнаружена";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        return OS.get();
    }

    public void add(Song song){
        songs.add(song);
    }

    public void deleteById(long id){
        Song song = getById(id);
        songs.remove(song);
    }

    public void addContainedInId(long id, long albumId){
        Song song = getById(id);

        if(song.getContainedInId().contains(albumId)) {
            String errorMassage = "Песня с индексом" + id + " уже есть в альбоме с индексом " + albumId;
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        song.getContainedInId().add(albumId);
    }

    public void removeContainedInId(long id, long albumId){
        Song song = getById(id);

        if(!song.getContainedInId().contains(albumId))
        {
            String errorMassage = "Песни с индексом" + id + " нет в альбоме с индексом " + albumId;
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        song.getContainedInId().remove(albumId);
    }

}
