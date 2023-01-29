package senla.dao;

import org.springframework.stereotype.Repository;
import senla.models.Album;
import senla.models.Song;

@Repository
public class AlbumDao {
    //Домашнее задание требовало реализации одного репозитория, по этому этот пустой

    public Album getAlbumById(long id){
        return null;
    }

    public void add(Album album){

    }

    public void delete(long id){

    }

    public void addSongsIn(long id, Song song){

    }

    public void removeSongsIn(long id, Song song){

    }
}
