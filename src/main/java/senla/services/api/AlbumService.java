package senla.services.api;

import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;

import java.util.List;

public interface AlbumService {

    Long save(AlbumCreateUpdateDataDto albumDto);

    AlbumInfoDto findAlbumInfoDtoById(Long id);

    void deleteById(Long id);

    void addSongIn(Long albumId, Long songId);

    void removeSongIn(Long albumId, Long songId);

    List<AlbumInfoDto> findSavedAlbumsInfoDtoFromAccountId(Long accountId);

    List<AlbumInfoDto> findCreatedAlbumInfoDtoFromAccountId(Long accountId);

    List<AlbumInfoDto> findAllAlbumInfoDto();

    List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title);
}
