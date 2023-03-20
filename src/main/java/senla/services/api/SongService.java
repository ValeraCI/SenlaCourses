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

    List<SongInfoDto> findSongsInfoDtoByGenreTitle(String genreTitle, String pageNumber, String limit);

    List<SongInfoDto> findSongsInfoDtoByTitle(String title, String pageNumber, String limit);

    List<SongInfoDto> findByParameter(String parameter, String findBy, String pageNumber, String limit);
}
