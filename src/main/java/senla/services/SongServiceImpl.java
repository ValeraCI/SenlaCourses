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
import senla.models.RoleTitle;
import senla.models.Song;
import senla.services.api.SongService;
import senla.util.Convertor;
import senla.util.Paginator;
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

        if (!hasAccess(song, accountDetails)) {
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
    public List<SongInfoDto> findSongsInfoDtoByGenreTitle(String genreTitle, String pageNumber, String limit) {
        Integer pageNumberInteger = Convertor.stringToInteger(pageNumber);
        Integer limitInteger = Convertor.stringToInteger(limit);

        limitInteger = Paginator.limitingMinimumValueToOne(limitInteger);


        Genre genre = genreDao.findByTitle(genreTitle);

        Long totalCount = songDao.getTotalCount();
        Integer firstResult = Paginator.getFirstElement(pageNumberInteger, totalCount, limitInteger);

        return songMapper.toSongInfoDtoList(
                songDao.findByGenre(genre, Math.toIntExact(firstResult), limitInteger)
        );
    }

    @Override
    public List<SongInfoDto> findSongsInfoDtoByTitle(String title, String pageNumber, String limit) {
        Integer pageNumberInteger = Convertor.stringToInteger(pageNumber);
        Integer limitInteger = Convertor.stringToInteger(limit);

        limitInteger = Paginator.limitingMinimumValueToOne(limitInteger);

        Long totalCount = songDao.getTotalCount();
        Integer firstResult = Paginator.getFirstElement(pageNumberInteger, totalCount, limitInteger);

        return songMapper.toSongInfoDtoList(
                songDao.findByTitle(title, Math.toIntExact(firstResult), limitInteger)
        );
    }

    @Override
    public List<SongInfoDto> findByParameter(String parameter, String findBy, String pageNumber, String limit) {
        List<SongInfoDto> resultList = null;
        SongFindParameter songFindParameter = Convertor.stringToSongFindParameter(findBy);

        switch (songFindParameter) {
            case BY_GENRE:
                resultList = findSongsInfoDtoByGenreTitle(parameter, pageNumber, limit);
                break;
            case BY_TITLE:
                resultList = findSongsInfoDtoByTitle(parameter, pageNumber, limit);
                break;
        }
        return resultList;
    }

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

    private boolean hasAccess(Song song, AccountDetails accountDetails) {
        return song.getAuthors()
                .stream()
                .map(AEntity::getId)
                .anyMatch(id -> id.equals(accountDetails.getId())) ||
                accountDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(auth -> auth.equals(RoleTitle.ROLE_ADMINISTRATOR.toString())
                                || auth.equals(RoleTitle.ROLE_OWNER.toString()));
    }
}
