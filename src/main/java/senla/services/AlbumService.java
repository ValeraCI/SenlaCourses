package senla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.SongDao;
import senla.dto.album.AlbumCreateDto;
import senla.dto.album.AlbumInfoDto;
import senla.dto.song.SongInfoDto;
import senla.models.Account;
import senla.models.Album;
import senla.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final SongDao songDao;

    @Transactional
    public void save(AlbumCreateDto albumDto){
        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        Account account = accountDao.findById(albumDto.getCreatorId());
        album.setCreator(account);
        album.setCreateDate(new Date());
        account.getCreatedAlbums().add(album);

        albumDao.save(album);
        accountDao.update(account);
    }

    @Transactional
    public AlbumInfoDto findAlbumInfoDtoById(Long id) {
        Album album = albumDao.findById(id);
        AlbumInfoDto albumInfoDto = new AlbumInfoDto(id, album.getTitle());
        return albumInfoDto ;
    }

    @Transactional
    public void deleteById(Long id){
        albumDao.deleteById(id);
    }

    @Transactional
    public void addSongIn(Long albumId, Long songId){
        Song song = songDao.findById(songId);

        albumDao.addSongIn(albumId, song);
    }

    @Transactional
    public void removeSongIn(Long albumId, Long songId){
        Song song = songDao.findById(songId);

        albumDao.removeSongIn(albumId, song);
    }

    @Transactional
    public List<SongInfoDto> findSongsIn(Long albumId){
        List<Song> songIn = albumDao.getSongsIn(albumId);

        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        for (Song song: songIn) {
            SongInfoDto songInfoDto = new SongInfoDto();
            songInfoDto.setId(song.getId());
            songInfoDto.setTitle(song.getTitle());
            List<String> authorsNicknames = new ArrayList<>();
            for (Account author: song.getAuthors()) {
                authorsNicknames.add(author.getNickname());
            }
            songInfoDto.setAuthorsNicknames(authorsNicknames);

            songInfoDtoList.add(songInfoDto);
        }

        return songInfoDtoList;
    }
}
