package senla.services.api;

import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.models.AccountDetails;

import java.util.List;

public interface AlbumService {

    Long save(AlbumCreateUpdateDataDto albumDto);

    AlbumInfoDto findAlbumInfoDtoById(Long id);

    void deleteById(Long id, AccountDetails accountDetails);

    void addSongIn(Long albumId, Long songId, AccountDetails accountDetails);

    void removeSongIn(Long albumId, Long songId, AccountDetails accountDetails);

    List<AlbumInfoDto> findSavedAlbumsInfoDtoFromAccountId(Long accountId);

    List<AlbumInfoDto> findCreatedAlbumInfoDtoFromAccountId(Long accountId);

    List<AlbumInfoDto> findAllAlbumInfoDto();

    List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title);

    List<AlbumInfoDto> findRecommendedFor(AccountDetails accountDetails, Integer limit);
}
