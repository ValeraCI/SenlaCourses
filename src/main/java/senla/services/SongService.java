package senla.services;

import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import java.util.List;

public interface SongService{

    Long save(SongCreateDto songCreateDto);

    void deleteById(Long id);

    List<SongInfoDto> findByAlbumId(Long albumId);

    SongInfoDto findSongInfoDtoById(Long id);

    List<SongInfoDto> findByGenreTitle(String genreTitle);

    List<SongInfoDto> findByTitle(String title);

    List<SongInfoDto> findByParameter(String parameter, String findBy);
}
