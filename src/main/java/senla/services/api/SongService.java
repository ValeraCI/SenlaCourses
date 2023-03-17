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

    List<SongInfoDto> findSongsInfoDtoByGenreTitle(String genreTitle, Long pageNumber, Integer limit);

    List<SongInfoDto> findSongsInfoDtoByTitle(String title, Long pageNumber, Integer limit);

    List<SongInfoDto> findByParameter(String parameter, String findBy, Long pageNumber, Integer limit);
}
