package senla.services.api;

import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.AccountDetails;

import java.util.List;

public interface SongService {

    Long save(SongCreateDto songCreateDto);

    void deleteById(Long id, AccountDetails accountDetails);

    List<SongInfoDto> findSongInfoDtoByAlbumId(Long albumId);

    SongInfoDto findSongInfoDtoById(Long id);

    List<SongInfoDto> findSongInfoDtoByGenreTitle(String genreTitle, Long pageNumber);

    List<SongInfoDto> findSongInfoDtoByTitle(String title, Long pageNumber);

    List<SongInfoDto> findByParameter(String parameter, String findBy, Long pageNumber);
}
