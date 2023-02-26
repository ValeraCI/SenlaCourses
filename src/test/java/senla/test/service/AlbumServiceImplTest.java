package senla.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.SongDao;
import senla.dto.album.AlbumInfoDto;
import senla.dto.album.CreateAlbumDto;
import senla.exceptions.DataChangesException;
import senla.models.*;
import senla.services.api.AlbumService;
import senla.services.AlbumServiceImpl;
import senla.test.configuration.WebMvcConfig;
import senla.util.mappers.AlbumMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {WebMvcConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class AlbumServiceImplTest {

    @Mock
    private AlbumDao albumDao;
    @Mock
    private AccountDao accountDao;
    @Mock
    private SongDao songDao;

    @Autowired
    private AlbumMapper albumMapper;

    private AlbumService albumService;

    public AlbumServiceImplTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void beforeEach(){
        albumService = new AlbumServiceImpl(albumDao, accountDao, songDao, albumMapper);
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
        song.setTitle("TestSong");
        return song;
    }

    @Test
    public void saveTest(){
        given(accountDao.findById(1L)).willReturn(createAccount());

        CreateAlbumDto albumDto = new CreateAlbumDto("test", 1L);

        Assert.assertEquals(0L, albumService.save(albumDto).longValue());
    }

    @Test
    public void findAlbumInfoDtoById() {
        given(albumDao.findById(1L))
                .willReturn(createAlbum());

        AlbumInfoDto album = albumService.findAlbumInfoDtoById(1L);

        Assert.assertEquals("TestAlbum", album.getTitle());
        Assert.assertEquals(1L, album.getId());
    }

    @Test
    public void deleteById(){
        albumService.deleteById(1L);

        verify(albumDao).deleteById(1L);
    }

    @Test
    public void addSongInNoExceptionTest(){
        Album album = createAlbum();

        given(songDao.findById(1L))
                .willReturn(createSong());
        given(albumDao.findById(1L))
                .willReturn(album);

        albumService.addSongIn(1L, 1L);

        Assert.assertEquals(album.getSongsIn().size(), 1);
    }

    @Test(expected = DataChangesException.class)
    public void addSongInExceptionTest(){
        Album album = createAlbum();

        given(songDao.findById(1L))
                .willReturn(createSong());
        given(albumDao.findById(1L))
                .willReturn(album);

        albumService.addSongIn(1L, 1L);
        albumService.addSongIn(1L, 1L);
    }

    @Test
    public void removeSavedAlbumNoExceptionTest(){
        Album album = createAlbum();
        Song song = createSong();


        given(songDao.findById(1L))
                .willReturn(song);
        given(albumDao.findById(1L))
                .willReturn(album);

        album.getSongsIn().add(song);
        albumService.removeSongIn(1L, 1L);

        Assert.assertEquals(album.getSongsIn().size(), 0);
    }

    @Test(expected = DataChangesException.class)
    public void removeSavedAlbumExceptionTest(){
        Album album = createAlbum();
        Song song = createSong();


        given(songDao.findById(1L))
                .willReturn(song);
        given(albumDao.findById(1L))
                .willReturn(album);


        album.getSongsIn().add(song);
        albumService.removeSongIn(1L, 1L);
        albumService.removeSongIn(1L, 1L);
    }

    @Test
    public void findSavedAlbumsInfoDtoFromAccountIdTest(){
        given(albumDao.findSavedFromByAccountId(1L))
                .willReturn(new ArrayList<>(Arrays.asList(createAlbum())));

        Assert.assertEquals(1, albumService.findSavedAlbumsInfoDtoFromAccountId(1L).size());
    }

    @Test
    public void findCreatedAlbumInfoDtoFromAccountIdTest(){
        given(albumDao.findCreatedFromAccountId(1L))
                .willReturn(new ArrayList<>(Arrays.asList(createAlbum())));

        Assert.assertEquals(1, albumService.findCreatedAlbumInfoDtoFromAccountId(1L).size());
    }

    @Test
    public void findAllAlbumInfoDtoTest(){
        given(albumDao.findAll())
                .willReturn(new ArrayList<>(Arrays.asList(createAlbum())));

        Assert.assertEquals(1, albumService.findAllAlbumInfoDto().size());
    }

    @Test
    public void findAlbumInfoDtoByTitleTest(){
        given(albumDao.findByTitle("test"))
                .willReturn(new ArrayList<>(Arrays.asList(createAlbum())));

        Assert.assertEquals(1, albumService.findAlbumInfoDtoByTitle("test").size());
    }
}