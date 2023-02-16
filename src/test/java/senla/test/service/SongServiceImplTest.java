package senla.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.*;
import senla.services.SongService;
import senla.services.SongServiceImpl;
import senla.test.configuration.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class SongServiceImplTest {

    @Mock
    private SongDao songDao;

    @Mock
    private AlbumDao albumDao;

    @Mock
    private AccountDao accountDao;

    @Mock
    private GenreDao genreDao;

    private SongService songService;

    public SongServiceImplTest(){
        MockitoAnnotations.openMocks(this);
        this.songService = new SongServiceImpl(songDao, albumDao, accountDao, genreDao);
    }

    private Genre createGenre(){
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setGenreTitle(GenreTitle.BLUES);

        return genre;
    }

    private Album createAlbum(){
        Album album = new Album();

        album.setId(1L);
        album.setTitle("TestAlbum");
        album.setSongsIn(new HashSet<>());
        album.setCreateDate(LocalDate.now());
        album.setCreator(createAccount());

        return album;
    }

    private Song createSong(){
        Song song = new Song();

        song.setId(1L);
        song.setTitle("TestSong");
        song.setGenre(createGenre());
        Location location = new Location(song, "./somePath");
        song.setLocation(location);
        song.setAuthors(new ArrayList(Arrays.asList(createAccount())));
        return song;
    }

    private Account createAccount(){
        Account account = new Account();

        account.setId(1L);
        account.setNickname("Tester");
        account.setCreatedAlbums(new HashSet<>());
        account.setRegistrationDate(LocalDate.now());
        account.setLoginDetails(new LoginDetails(account, "test@mail.ru", "1234"));
        return account;
    }

    @Test
    public void saveTest(){
        given(accountDao.findById(1L)).willReturn(createAccount());
        given(genreDao.findById(1L)).willReturn(createGenre());
        given(albumDao.findById(1L)).willReturn(createAlbum());

        SongCreateDto songCreateDto = new SongCreateDto();
        songCreateDto.setAuthorsId(new ArrayList<>(List.of(1L)));
        songCreateDto.setTitle("TestSong");
        songCreateDto.setGenreId(1L);
        songCreateDto.setAlbumCreator("Tester");
        songCreateDto.setAlbumId(1L);

        Assert.assertEquals(0L, songService.save(songCreateDto).longValue());
    }

    @Test
    public void deleteByIdTest(){
        songService.deleteById(1L);

        verify(songDao).deleteById(1L);
    }

    @Test
    public void findSongInfoDtoByAlbumIdTest(){
        given(songDao.findByAlbumId(1L))
                .willReturn(new ArrayList<>(List.of(createSong())));

        SongInfoDto songInfoDto = songService.findSongInfoDtoByAlbumId(1L).get(0);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }

    @Test
    public void findSongInfoDtoByIdTest() {
        given(songDao.findById(1L))
                .willReturn(createSong());

        SongInfoDto songInfoDto = songService.findSongInfoDtoById(1L);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }

    @Test
    public void findSongInfoDtoByGenreTitleTest() {
        Genre genre = createGenre();

        given(genreDao.findByTitle("BLUES"))
                .willReturn(genre);

        given(songDao.findByGenre(genre))
                .willReturn(new ArrayList<>(List.of(createSong())));

        SongInfoDto songInfoDto = songService.findSongInfoDtoByGenreTitle("BLUES").get(0);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }

    @Test
    public void findSongInfoDtoByTitle() {
        given(songDao.findByTitle("TestSong"))
                .willReturn(new ArrayList<>(List.of(createSong())));

        SongInfoDto songInfoDto = songService.findSongInfoDtoByTitle("TestSong").get(0);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }

    @Test
    public void findByParameterGenreTest() {
        Genre genre = createGenre();

        given(genreDao.findByTitle("BLUES"))
                .willReturn(genre);

        given(songDao.findByGenre(genre))
                .willReturn(new ArrayList<>(List.of(createSong())));

        SongInfoDto songInfoDto =  songService.findByParameter("BLUES", "BY_GENRE").get(0);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }

    @Test
    public void findByParameterTitleTest() {
        given(songDao.findByTitle("TestSong"))
                .willReturn(new ArrayList<>(List.of(createSong())));

        SongInfoDto songInfoDto = songService.findByParameter("TestSong", "BY_TITLE").get(0);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }

    @Test
    public void findByParameterAlbumIdTest() {
        given(songDao.findByAlbumId(1L))
                .willReturn(new ArrayList<>(List.of(createSong())));

        SongInfoDto songInfoDto = songService.findByParameter("1", "BY_ALBUM_ID").get(0);

        Assert.assertEquals("TestSong", songInfoDto.getTitle());
        Assert.assertEquals(1L, songInfoDto.getId());
    }
}
