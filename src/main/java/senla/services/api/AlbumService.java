package senla.services.api;

import senla.dto.album.AlbumCreateDto;
import senla.dto.album.AlbumInfoDto;
import senla.dto.album.AlbumUpdateDto;
import senla.models.AccountDetails;

import java.util.List;

public interface AlbumService {

    Long save(AlbumCreateDto albumDto);

    void updateData(Long id, AlbumUpdateDto albumDto, AccountDetails accountDetails);

    AlbumInfoDto findAlbumInfoDtoById(Long id);

    void deleteById(Long id, AccountDetails accountDetails);

    void addSongIn(Long albumId, Long songId, AccountDetails accountDetails);

    void removeSongIn(Long albumId, Long songId, AccountDetails accountDetails);

    List<AlbumInfoDto> findSavedAlbumsInfoDtoFromAccountId(Long accountId);

    List<AlbumInfoDto> findCreatedAlbumInfoDtoFromAccountId(Long accountId);

    List<AlbumInfoDto> findAllAlbumInfoDto(Long firstResult);

    List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title, Long pageNumber);

    List<AlbumInfoDto> findRecommendedFor(AccountDetails accountDetails, Integer limit);
}
