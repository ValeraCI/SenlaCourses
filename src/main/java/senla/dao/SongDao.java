package senla.dao;


import org.springframework.stereotype.Repository;
import senla.models.Song;
import java.util.List;

@Repository
public class SongDao{
    //Домашнее задание требовало реализации одного репозитория, по этому этот пустой

    public Song getSongById(long id){
        return null;
    }

    public void add(Song song){

    }

    public void delete(long id){

    }

    public List<Song> getSongsByListId(long listId){
        return null;
    }
}
