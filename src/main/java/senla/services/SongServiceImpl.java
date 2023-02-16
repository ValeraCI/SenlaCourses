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
import senla.util.SongFindParameter;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{
    private final SongDao songDao;
    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final GenreDao genreDao;

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
    public Long save(SongCreateDto songCreateDto){
        Song song = new Song();
        List<Account> authors = new ArrayList<>();

        for (Long authorId: songCreateDto.getAuthorsId()){
            authors.add(accountDao.findById(authorId));
        }

        song.setAuthors(authors);
        song.setTitle(songCreateDto.getTitle());
        Genre genre = genreDao.findById(songCreateDto.getGenreId());
        song.setGenre(genre);

        song.setLocation(new Location(song, createLocation(songCreateDto)));

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
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        for (Song song: songDao.findByAlbumId(albumId)) {
            songInfoDtoList.add(songToSongInfoDto(song));
        }

        return songInfoDtoList;
    }

    @Override
    @Transactional
    public SongInfoDto findSongInfoDtoById(Long id) {
        return songToSongInfoDto(songDao.findById(id));
    }

    @Override
    @Transactional
    public List<SongInfoDto> findSongInfoDtoByGenreTitle(String genreTitle) {
        Genre genre = genreDao.findByTitle(genreTitle);

        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        for (Song song : songDao.findByGenre(genre)) {
            songInfoDtoList.add(songToSongInfoDto(song));
        }
        return songInfoDtoList;
    }

    @Override
    @Transactional
    public List<SongInfoDto> findSongInfoDtoByTitle(String title) {
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        for (Song song : songDao.findByTitle(title)) {
            songInfoDtoList.add(songToSongInfoDto(song));
        }
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


    private SongInfoDto songToSongInfoDto(Song song){
        SongInfoDto songInfoDto = new SongInfoDto();
        songInfoDto.setId(song.getId());
        songInfoDto.setTitle(song.getTitle());

        List<String> authorsNicknames = new ArrayList<>();
        for (Account author: song.getAuthors()) {
            authorsNicknames.add(author.getNickname());
        }
        songInfoDto.setAuthorsNicknames(authorsNicknames);

        return songInfoDto;
    }
}
