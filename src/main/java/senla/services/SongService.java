package senla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senla.dao.SongDao;
import senla.dto.SongCreateDto;
import senla.dto.SongInfoDto;
import senla.models.Location;
import senla.models.Song;

@Service
public class SongService{
    private SongDao songDao;

    @Autowired
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    private Location createLocation(Song song, SongCreateDto songCreateDto){
        /*StringBuilder sb = new StringBuilder(".\\music\\");
        sb.append(songCreateDto.getAlbumCreator());
        sb.append("\\");
        sb.append(albumService.getAlbumById(songCreateDto.getAlbumId()).getTitle());
        sb.append("\\");
        sb.append(songCreateDto.getTitle());
        sb.append(".mp3");

        return new Location(song, sb.toString());
        */
        return null;
    }

    public void add(SongCreateDto songCreateDto){
    }

    public Song getSongById(long id) {
        return songDao.getSongById(id);
    }

    public SongInfoDto getSongInfoDtoById(long id) {
        return null;
    }

    public void deleteById(long id){;
        songDao.delete(id);
    }
}
