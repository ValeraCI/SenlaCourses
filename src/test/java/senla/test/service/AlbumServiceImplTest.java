package senla.test.service;

import org.apache.commons.math3.stat.inference.MannWhitneyUTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import senla.configuration.WebMvcConfig;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.SongDao;
import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.AccountDetails;
import senla.models.Album;
import senla.models.Song;
import senla.services.AlbumServiceImpl;
import senla.test.util.ObjectCreator;
import senla.util.mappers.AlbumMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class AlbumServiceImplTest {
    @Mock
    private AlbumDao albumDao;
    @Mock
    private AccountDao accountDao;
    @Mock
    private SongDao songDao;
    @Mock
    private AlbumMapper albumMapper;
    @Mock
    private MannWhitneyUTest mannWhitneyUTest;
    private AlbumServiceImpl albumService;

    @BeforeEach
    public void setup() {
        albumService = new AlbumServiceImpl(albumDao, accountDao, songDao, albumMapper, mannWhitneyUTest,
                500L, 0.1);
    }

    public AlbumServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAlbum() {
        AlbumCreateUpdateDataDto albumDto = new AlbumCreateUpdateDataDto();
        albumDto.setCreatorId(1L);
        Account account = new Account();
        Album album = new Album();

        when(accountDao.findById(anyLong())).thenReturn(account);
        when(albumMapper.toEntity(albumDto, account)).thenReturn(album);
        when(albumDao.save(album)).thenReturn(1L);

        Long albumId = albumService.save(albumDto);

        assertEquals(Long.valueOf(1), albumId);
        verify(albumDao).save(album);
        verify(accountDao).findById(anyLong());
        verify(albumMapper).toEntity(albumDto, account);
    }

    @Test
    public void testFindAlbumInfoDtoById() {
        Album album = new Album();
        AlbumInfoDto albumInfoDto = new AlbumInfoDto();

        when(albumDao.findById(anyLong()))
                .thenReturn(album);
        when(albumMapper.toAlbumInfoDto(album)).thenReturn(albumInfoDto);

        AlbumInfoDto result = albumService.findAlbumInfoDtoById(1L);

        assertEquals(albumInfoDto, result);
        verify(albumDao).findById(1L);
        verify(albumMapper).toAlbumInfoDto(album);
    }

    @Test
    public void testDeleteById() {
        Account account = ObjectCreator.createAccount();
        Album album = new Album();
        album.setCreator(account);

        when(albumDao.findByIdWithCreator(anyLong())).thenReturn(album);

        albumService.deleteById(1L, new AccountDetails(account));

        verify(albumDao).findByIdWithCreator(1L);
        verify(albumDao).deleteById(1L);
    }

    @Test
    public void testAddSongIn() {
        Account account = ObjectCreator.createAccount();
        Song song = new Song();
        Album album = new Album();
        album.setSongsIn(new HashSet<>());
        album.setCreator(account);

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findByIdWithCreator(anyLong())).thenReturn(album);

        albumService.addSongIn(1L, 1L, new AccountDetails(account));

        assertEquals(album.getSongsIn().size(), 1);
        verify(songDao).findById(1L);
        verify(albumDao).findByIdWithCreator(1L);
    }

    @Test
    public void testAddSongInException() {
        Account account = ObjectCreator.createAccount();

        Song song = new Song();
        Album album = new Album();
        album.setCreator(account);

        album.setSongsIn(new HashSet<>());
        album.getSongsIn().add(song);

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findByIdWithCreator(anyLong())).thenReturn(album);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            albumService.addSongIn(1L, 1L, new AccountDetails(account));
        });

        assertEquals("Album already contains such a song", dataChangesException.getMessage());
        verify(songDao).findById(1L);
        verify(albumDao).findByIdWithCreator(1L);
    }

    @Test
    public void testRemoveSavedAlbum() {
        Account account = ObjectCreator.createAccount();

        Song song = new Song();

        Album album = new Album();
        album.setCreator(account);
        album.setSongsIn(new HashSet<>());
        album.getSongsIn().add(song);

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findByIdWithCreator(anyLong())).thenReturn(album);

        albumService.removeSongIn(1L, 1L, new AccountDetails(account));

        assertEquals(album.getSongsIn().size(), 0);
        verify(songDao).findById(1L);
        verify(albumDao).findByIdWithCreator(1L);
    }

    @Test
    public void testRemoveSavedAlbumException() {
        Account account = ObjectCreator.createAccount();

        Song song = new Song();
        Album album = new Album();
        album.setSongsIn(new HashSet<>());
        album.setCreator(account);

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findByIdWithCreator(anyLong())).thenReturn(album);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            albumService.removeSongIn(1L, 1L, new AccountDetails(account));
        });

        assertEquals("Album does not contain such a song", dataChangesException.getMessage());
        verify(songDao).findById(1L);
        verify(albumDao).findByIdWithCreator(1L);
    }

    @Test
    public void testFindSavedAlbumsInfoDtoFromAccountId() {
        List<Album> albums = new ArrayList<>();
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();

        when(albumDao.findSavedFromByAccountId(anyLong())).thenReturn(albums);
        when(albumMapper.toAlbumInfoDtoList(albums)).thenReturn(albumInfoDtoList);

        List<AlbumInfoDto> answer = albumService.findSavedAlbumsInfoDtoFromAccountId(1L);

        assertEquals(0, answer.size());
        assertEquals(albumInfoDtoList, answer);
        verify(albumDao).findSavedFromByAccountId(1L);
        verify(albumMapper).toAlbumInfoDtoList(albums);
    }

    @Test
    public void testFindCreatedAlbumInfoDtoFromAccountId() {
        List<Album> albums = new ArrayList<>();
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();

        when(albumDao.findCreatedFromAccountId(anyLong())).thenReturn(albums);
        when(albumMapper.toAlbumInfoDtoList(albums)).thenReturn(albumInfoDtoList);

        List<AlbumInfoDto> answer = albumService.findCreatedAlbumInfoDtoFromAccountId(1L);

        assertEquals(0, answer.size());
        assertEquals(albumInfoDtoList, answer);
        verify(albumDao).findCreatedFromAccountId(1L);
        verify(albumMapper).toAlbumInfoDtoList(albums);
    }

    @Test
    public void testFindAllAlbumInfoDto() {
        List<Album> albums = new ArrayList<>();
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();

        when(albumDao.findAll()).thenReturn(albums);
        when(albumMapper.toAlbumInfoDtoList(albums)).thenReturn(albumInfoDtoList);

        List<AlbumInfoDto> answer = albumService.findAllAlbumInfoDto();

        assertEquals(0, answer.size());
        assertEquals(albumInfoDtoList, answer);
        verify(albumDao).findAll();
        verify(albumMapper).toAlbumInfoDtoList(albums);
    }

    @Test
    public void testFindAlbumInfoDtoByTitle() {
        List<Album> albums = new ArrayList<>();
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();

        when(albumDao.findByTitle(anyString())).thenReturn(albums);
        when(albumMapper.toAlbumInfoDtoList(albums)).thenReturn(albumInfoDtoList);

        List<AlbumInfoDto> answer = albumService.findAlbumInfoDtoByTitle("song");

        assertEquals(0, answer.size());
        assertEquals(albumInfoDtoList, answer);
        verify(albumDao).findByTitle("song");
        verify(albumMapper).toAlbumInfoDtoList(albums);
    }

    @Test
    public void testFindRecommendedForLessThanThree() {
        Account account = ObjectCreator.createAccount();
        List<Album> albums = new ArrayList<>();
        List<AlbumInfoDto> result = new ArrayList<>();

        when(accountDao.findWithSavedAlbumsById(anyLong())).thenReturn(account);
        when(albumDao.findRandomExcept(anyInt(), any())).thenReturn(albums);
        when(albumMapper.toAlbumInfoDtoList(any())).thenReturn(result);

        List<AlbumInfoDto> answer = albumService.findRecommendedFor(new AccountDetails(account), 10);

        assertEquals(result, answer);
        verify(accountDao).findWithSavedAlbumsById(1L);
        verify(albumDao).findRandomExcept(eq(10), any());
        verify(albumMapper).toAlbumInfoDtoList(any());
    }

    @Test
    public void testFindRecommendedForMoreThanThree() {
        Long numberOfUsersAtTime = 500L;

        Account account = ObjectCreator.createAccount();
        Album album;
        for (long i = 1; i < 8; i++) {
            album = new Album();
            album.setId(i);
            account.getSavedAlbums().add(album);
        }

        List<AlbumInfoDto> result = new ArrayList<>();

        List<Account> accounts = new ArrayList<>();
        Account newAccount = ObjectCreator.createAccount();

        for (long i = 2; i < 10; i++) {
            album = new Album();
            album.setId(i);
            newAccount.getSavedAlbums().add(album);
        }

        accounts.add(newAccount);

        when(accountDao.findWithSavedAlbumsById(anyLong())).thenReturn(account);
        when(accountDao.findWithSavedAlbumsByIdInBetween(1L, numberOfUsersAtTime + 1)).thenReturn(accounts);
        when(mannWhitneyUTest.mannWhitneyUTest(any(), any())).thenReturn(0.1);
        when(accountDao.findWithSavedAlbumsByIdInBetween(numberOfUsersAtTime + 1, numberOfUsersAtTime * 2 + 1))
                .thenReturn(new ArrayList<>());
        when(albumMapper.toAlbumInfoDtoList(any())).thenReturn(result);

        List<AlbumInfoDto> answer = albumService.findRecommendedFor(new AccountDetails(account), 10);

        assertEquals(result, answer);

        verify(accountDao).findWithSavedAlbumsById(1L);
        verify(accountDao).findWithSavedAlbumsByIdInBetween(1L, numberOfUsersAtTime + 1);
        verify(mannWhitneyUTest).mannWhitneyUTest(any(), any());
        verify(accountDao).findWithSavedAlbumsByIdInBetween(numberOfUsersAtTime + 1, numberOfUsersAtTime * 2 + 1);
        verify(albumMapper).toAlbumInfoDtoList(any());
    }
}