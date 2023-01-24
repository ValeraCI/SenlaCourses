package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.SongDao;
import senla.dto.SongCreateDto;
import senla.dto.SongDto;
import senla.models.Song;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongService{
    private SongDao songDao;
    private AlbumService albumService;

    @Autowired
    public SongService(SongDao songDao, AlbumService albumService) {
        this.songDao = songDao;
        this.albumService = albumService;
    }

    private String createPath(SongCreateDto songCreateDto){
        StringBuilder sb = new StringBuilder(".\\music\\");
        sb.append(songCreateDto.getAlbumCreator());
        sb.append("\\");
        sb.append(albumService.getById(songCreateDto.getAlbumId()).getTitle());
        sb.append("\\");
        sb.append(songCreateDto.getTitle());
        sb.append(".mp3");
        return sb.toString();
    }

    public void add(SongCreateDto songCreateDto){
        String path = createPath(songCreateDto);
        Song song = new Song(songCreateDto.getId(), songCreateDto.getTitle(), songCreateDto.getGenre(),
                songCreateDto.getAuthorsId(), new ArrayList<>(List.of(songCreateDto.getAlbumId())), path);
        songDao.add(song);
    }

    public SongDto getById(long id) {
        Song song = songDao.getById(id);
        SongDto songDto = new SongDto(id, song.getTitle(), song.getGenre(), song.getAuthorsId());
        return songDto;
    }

    public void deleteById(long id){;
        songDao.deleteById(id);
    }

    public void addContainedInId(long id, long albumId){
       songDao.addContainedInId(id, albumId);
    }

    public void removeContainedInId(long id, long albumId){
       songDao.removeContainedInId(id, albumId);
    }

}
