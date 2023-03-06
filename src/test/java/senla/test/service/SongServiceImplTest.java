package senla.test.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.configuration.WebMvcConfig;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.Account;
import senla.models.Album;
import senla.models.Genre;
import senla.models.Song;
import senla.services.SongServiceImpl;
import senla.util.mappers.SongMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class SongServiceImplTest {

    @Mock
    private SongDao songDao;
    @Mock
    private AlbumDao albumDao;
    @Mock
    private AccountDao accountDao;
    @Mock
    private GenreDao genreDao;
    @Mock
    private SongMapper songMapper;
    @InjectMocks
    private SongServiceImpl songService;

    public SongServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        SongCreateDto songCreateDto = new SongCreateDto();
        songCreateDto.setAlbumId(1L);
        songCreateDto.setGenreId(1L);
        List<Account> authors = new ArrayList<>();
        Genre genre = new Genre();
        Song song = new Song();
        Album album = new Album();
        album.setTitle("Test");

        when(accountDao.findByIds(any())).thenReturn(authors);
        when(genreDao.findById(anyLong())).thenReturn(genre);
        when(albumDao.findById(anyLong())).thenReturn(album);
        when(songMapper.toEntity(eq(songCreateDto), eq(authors), eq(genre), anyString())).thenReturn(song);
        when(songDao.save(song)).thenReturn(1L);

        Long id = songService.save(songCreateDto);

        assertEquals(Long.valueOf(1), id);
        verify(accountDao).findByIds(any());
        verify(genreDao).findById(anyLong());
        verify(albumDao).findById(anyLong());
        verify(songMapper).toEntity(eq(songCreateDto), eq(authors), eq(genre), anyString());
        verify(songDao).save(song);
    }

    @Test
    public void testDeleteById() {
        songService.deleteById(1L);

        verify(songDao).deleteById(1L);
    }

    @Test
    public void testFindSongInfoDtoByAlbumId() {
        List<Song> songs = new ArrayList<>();
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        when(songDao.findByAlbumId(anyLong())).thenReturn(songs);
        when(songMapper.toSongInfoDtoList(songs)).thenReturn(songInfoDtoList);

        List<SongInfoDto> result = songService.findSongInfoDtoByAlbumId(1L);

        assertEquals(songInfoDtoList, result);
        verify(songDao).findByAlbumId(1L);
        verify(songMapper).toSongInfoDtoList(songs);
    }

    @Test
    public void testFindSongInfoDtoById() {
        Song song = new Song();
        SongInfoDto songInfoDto = new SongInfoDto();

        when(songDao.findById(anyLong())).thenReturn(song);
        when(songMapper.toSongInfoDto(song)).thenReturn(songInfoDto);

        SongInfoDto result = songService.findSongInfoDtoById(1L);

        assertEquals(songInfoDto, result);
        verify(songDao).findById(1L);
        verify(songMapper).toSongInfoDto(song);
    }

    @Test
    public void testFindSongInfoDtoByGenreTitle() {
        Genre genre = new Genre();
        List<Song> songs = new ArrayList<>();
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        when(genreDao.findByTitle(anyString())).thenReturn(genre);
        when(songDao.findByGenre(genre)).thenReturn(songs);
        when(songMapper.toSongInfoDtoList(songs)).thenReturn(songInfoDtoList);

        List<SongInfoDto> result = songService.findSongInfoDtoByGenreTitle("BLUES");

        assertEquals(songInfoDtoList, result);
        verify(genreDao).findByTitle("BLUES");
        verify(songDao).findByGenre(genre);
        verify(songMapper).toSongInfoDtoList(songs);
    }

    @Test
    public void testFindSongInfoDtoByTitle() {
        List<Song> songs = new ArrayList<>();
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        when(songDao.findByTitle(anyString())).thenReturn(songs);
        when(songMapper.toSongInfoDtoList(songs)).thenReturn(songInfoDtoList);

        List<SongInfoDto> result = songService.findSongInfoDtoByTitle("Test");

        assertEquals(songInfoDtoList, result);
        verify(songDao).findByTitle("Test");
        verify(songMapper).toSongInfoDtoList(songs);
    }

    @Test
    public void testFindByParameterGenre() {
        Genre genre = new Genre();
        List<Song> songs = new ArrayList<>();
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        when(genreDao.findByTitle(anyString())).thenReturn(genre);
        when(songDao.findByGenre(genre)).thenReturn(songs);
        when(songMapper.toSongInfoDtoList(songs)).thenReturn(songInfoDtoList);

        List<SongInfoDto> result = songService.findByParameter("BLUES", "BY_GENRE");

        assertEquals(songInfoDtoList, result);
        verify(genreDao).findByTitle("BLUES");
        verify(songDao).findByGenre(genre);
        verify(songMapper).toSongInfoDtoList(songs);
    }

    @Test
    public void testFindByParameterTitle() {
        List<Song> songs = new ArrayList<>();
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        when(songDao.findByTitle(anyString())).thenReturn(songs);
        when(songMapper.toSongInfoDtoList(songs)).thenReturn(songInfoDtoList);

        List<SongInfoDto> result = songService.findByParameter("Test", "BY_TITLE");

        assertEquals(songInfoDtoList, result);
        verify(songDao).findByTitle("Test");
        verify(songMapper).toSongInfoDtoList(songs);
    }

    @Test
    public void testFindByParameterAlbumId() {
        List<Song> songs = new ArrayList<>();
        List<SongInfoDto> songInfoDtoList = new ArrayList<>();

        when(songDao.findByAlbumId(anyLong())).thenReturn(songs);
        when(songMapper.toSongInfoDtoList(songs)).thenReturn(songInfoDtoList);

        List<SongInfoDto> result = songService.findByParameter("1", "BY_ALBUM_ID");

        assertEquals(songInfoDtoList, result);
        verify(songDao).findByAlbumId(1L);
        verify(songMapper).toSongInfoDtoList(songs);
    }
}
