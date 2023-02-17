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
import senla.services.api.AlbumService;
import senla.util.mappers.AlbumMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final SongDao songDao;
    private final AlbumMapper albumMapper;

    @Override
    public Long save(CreateAlbumDto albumDto){
        Account account = accountDao.findById(albumDto.getCreatorId());

        Album album = albumMapper.toEntity(albumDto, account);

        account.getCreatedAlbums().add(album);

        return albumDao.save(album);
    }

    @Override
    public AlbumInfoDto findAlbumInfoDtoById(Long id) {
        Album album = albumDao.findById(id);
        return albumMapper.toAlbumInfoDto(album);
    }

    @Override
    public void deleteById(Long id){
        albumDao.deleteById(id);
    }

    @Override
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

    @Override
    public List<AlbumInfoDto> findSavedAlbumsInfoDtoFromAccountId(Long accountId){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findSavedFromByAccountId(accountId)){
            albumInfoDtoList.add(albumMapper.toAlbumInfoDto(album));
        }
        return albumInfoDtoList;
    }

    @Override
    public List<AlbumInfoDto> findCreatedAlbumInfoDtoFromAccountId(Long accountId){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findCreatedFromAccountId(accountId)){
            albumInfoDtoList.add(albumMapper.toAlbumInfoDto(album));
        }
        return albumInfoDtoList;
    }

    @Override
    public List<AlbumInfoDto> findAllAlbumInfoDto(){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findAll()){
            albumInfoDtoList.add(albumMapper.toAlbumInfoDto(album));
        }
        return albumInfoDtoList;
    }

    @Override
    public List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albumDao.findByTitle(title)){
            albumInfoDtoList.add(albumMapper.toAlbumInfoDto(album));
        }
        return albumInfoDtoList;
    }
}
