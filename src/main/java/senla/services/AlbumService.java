package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.SongDao;
import senla.dto.album.CreateAlbumDto;
import senla.dto.album.AlbumInfoDto;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.Album;
import senla.models.Song;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {

    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final SongDao songDao;

    public Long save(CreateAlbumDto albumDto){
        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        Account account = accountDao.findById(albumDto.getCreatorId());
        album.setCreator(account);
        album.setCreateDate(LocalDate.now());
        account.getCreatedAlbums().add(album);

        return albumDao.save(album);
    }

    public AlbumInfoDto findAlbumInfoDtoById(Long id) {
        Album album = albumDao.findById(id);
        return new AlbumInfoDto(id, album.getTitle());
    }

    public void deleteById(Long id){
        albumDao.deleteById(id);
    }

    public void addSongIn(Long albumId, Long songId){
        Song song = songDao.findById(songId);
        Album album = albumDao.findById(albumId);

        if(!album.getSongsIn().contains(song)){
            album.getSongsIn().add(song);
        }
        else {
            throw new DataChangesException("Альбом уже содержит такую песню");
        }
    }

    public void removeSongIn(Long albumId, Long songId){
        Song song = songDao.findById(songId);
        Album album = albumDao.findById(albumId);

        if(album.getSongsIn().contains(song)){
            album.getSongsIn().remove(song);
        }
        else {
            throw new DataChangesException("Альбом не содержит такую песню");
        }
    }

    public List<AlbumInfoDto> findSavedAlbumsInfoDtoFromAccountId(Long accountId){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findSavedFromByAccountId(accountId)){
            albumInfoDtoList.add(new AlbumInfoDto(album.getId(), album.getTitle()));
        }
        return albumInfoDtoList;
    }

    public List<AlbumInfoDto> findCreatedAlbumInfoDtoFromAccountId(Long accountId){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findCreatedFromAccountId(accountId)){
            albumInfoDtoList.add(new AlbumInfoDto(album.getId(), album.getTitle()));
        }
        return albumInfoDtoList;
    }

    public List<AlbumInfoDto> findAllAlbumInfoDto(){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findAll()){
            albumInfoDtoList.add(new AlbumInfoDto(album.getId(), album.getTitle()));
        }
        return albumInfoDtoList;
    }

    public List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findByTitle(title)){
            albumInfoDtoList.add(new AlbumInfoDto(album.getId(), album.getTitle()));
        }
        return albumInfoDtoList;
    }
}
