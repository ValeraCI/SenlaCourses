package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.*;
import senla.services.api.SongService;
import senla.util.SongFindParameter;
import senla.util.mappers.SongMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongDao songDao;
    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final GenreDao genreDao;
    private final SongMapper songMapper;

    private String createLocation(SongCreateDto songCreateDto){
        StringBuilder sb = new StringBuilder(".\\music\\");
        sb.append(songCreateDto.getAlbumCreator());
        sb.append("\\");
        sb.append(albumDao.findById(songCreateDto.getAlbumId()).getTitle());
        sb.append("\\");
        sb.append(songCreateDto.getTitle());
        sb.append(".mp3");

        return sb.toString();
    }

    @Override
    @Transactional
    public Long save(SongCreateDto songCreateDto) {
        List<Account> authors = new ArrayList<>();
        for (Long authorId : songCreateDto.getAuthorsId()) {
            authors.add(accountDao.findById(authorId));
        }
        Genre genre = genreDao.findById(songCreateDto.getGenreId());

        Song song = songMapper.toEntity(songCreateDto, authors, genre, createLocation(songCreateDto));
        return songDao.save(song);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        songDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<SongInfoDto> findSongInfoDtoByAlbumId(Long albumId){
        List<SongInfoDto> songInfoDtoList =
                songMapper.toSongInfoDtoList(
                        songDao.findByAlbumId(albumId)
                );

        return songInfoDtoList;
    }

    @Override
    @Transactional
    public SongInfoDto findSongInfoDtoById(Long id) {
        return songMapper.toSongInfoDto(songDao.findById(id));
    }

    @Override
    @Transactional
    public List<SongInfoDto> findSongInfoDtoByGenreTitle(String genreTitle) {
        Genre genre = genreDao.findByTitle(genreTitle);

        List<SongInfoDto> songInfoDtoList =
                songMapper.toSongInfoDtoList(
                        songDao.findByGenre(genre)
                );

        return songInfoDtoList;
    }

    @Override
    @Transactional
    public List<SongInfoDto> findSongInfoDtoByTitle(String title) {
        List<SongInfoDto> songInfoDtoList =
                songMapper.toSongInfoDtoList(
                        songDao.findByTitle(title)
                );

        return songInfoDtoList;
    }

    @Override
    @Transactional
    public List<SongInfoDto> findByParameter(String parameter, String findBy) {
        List<SongInfoDto> resultList = null;
        SongFindParameter songFindParameter = SongFindParameter.valueOf(findBy);

        switch (songFindParameter){
            case BY_GENRE:
                resultList = findSongInfoDtoByGenreTitle(parameter);
                break;
            case BY_TITLE:
                resultList = findSongInfoDtoByTitle(parameter);
                break;
            case BY_ALBUM_ID:
                resultList = findSongInfoDtoByAlbumId(Long.parseLong(parameter));
                break;
        }
        return resultList;
    }
}
