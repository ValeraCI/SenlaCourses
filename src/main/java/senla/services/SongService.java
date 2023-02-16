package senla.services;

import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import java.util.List;

public interface SongService{

    Long save(SongCreateDto songCreateDto);

    void deleteById(Long id);

    List<SongInfoDto> findSongInfoDtoByAlbumId(Long albumId);

    SongInfoDto findSongInfoDtoById(Long id);

    List<SongInfoDto> findSongInfoDtoByGenreTitle(String genreTitle);

    List<SongInfoDto> findSongInfoDtoByTitle(String title);

    List<SongInfoDto> findByParameter(String parameter, String findBy);
}
