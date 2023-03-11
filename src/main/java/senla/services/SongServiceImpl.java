package senla.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.exceptions.InsufficientRightsException;
import senla.models.AEntity;
import senla.models.Account;
import senla.models.AccountDetails;
import senla.models.Genre;
import senla.models.Song;
import senla.services.api.SongService;
import senla.util.SongFindParameter;
import senla.util.mappers.SongMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SongServiceImpl implements SongService {

    private final SongDao songDao;
    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final GenreDao genreDao;
    private final SongMapper songMapper;

    private String createLocation(SongCreateDto songCreateDto) {
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
    public Long save(SongCreateDto songCreateDto) {
        List<Account> authors = accountDao.findByIds(songCreateDto.getAuthorsId());
        Genre genre = genreDao.findById(songCreateDto.getGenreId());

        Song song = songMapper.toEntity(songCreateDto, authors, genre, createLocation(songCreateDto));
        return songDao.save(song);
    }

    @Override
    public void deleteById(Long id, AccountDetails accountDetails) {
        Song song = songDao.findById(id);

        if(!hasAccess(song, accountDetails)){
            throw new InsufficientRightsException("You can't delete a song");
        }

        songDao.deleteById(id);
    }

    @Override
    public List<SongInfoDto> findSongInfoDtoByAlbumId(Long albumId) {
        return songMapper.toSongInfoDtoList(
                        songDao.findByAlbumId(albumId)
                );
    }

    @Override
    public SongInfoDto findSongInfoDtoById(Long id) {
        return songMapper.toSongInfoDto(songDao.findById(id));
    }

    @Override
    public List<SongInfoDto> findSongInfoDtoByGenreTitle(String genreTitle) {
        Genre genre = genreDao.findByTitle(genreTitle);

        return songMapper.toSongInfoDtoList(
                        songDao.findByGenre(genre)
                );
    }

    @Override
    public List<SongInfoDto> findSongInfoDtoByTitle(String title) {
        return songMapper.toSongInfoDtoList(
                        songDao.findByTitle(title)
                );
    }

    @Override
    public List<SongInfoDto> findByParameter(String parameter, String findBy) {
        List<SongInfoDto> resultList = null;
        SongFindParameter songFindParameter = SongFindParameter.valueOf(findBy);

        switch (songFindParameter) {
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

    private boolean hasAccess(Song song, AccountDetails accountDetails){
        return song.getAuthors()
                .stream()
                .map(AEntity::getId)
                .anyMatch(id -> id.equals(accountDetails.getId())) ||
                accountDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(auth -> auth.equals("ADMINISTRATOR")
                                || auth.equals("OWNER"));
    }
}
