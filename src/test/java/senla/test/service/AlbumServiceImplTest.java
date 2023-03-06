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
import senla.dao.SongDao;
import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.exceptions.DataChangesException;
import senla.models.Account;
import senla.models.Album;
import senla.models.Song;
import senla.services.AlbumServiceImpl;
import senla.util.mappers.AlbumMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
    @InjectMocks
    private AlbumServiceImpl albumService;

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
        albumService.deleteById(1L);

        verify(albumDao).deleteById(1L);
    }

    @Test
    public void testAddSongIn() {
        Song song = new Song();
        Album album = new Album();
        album.setSongsIn(new HashSet<>());

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findById(anyLong())).thenReturn(album);

        albumService.addSongIn(1L, 1L);

        assertEquals(album.getSongsIn().size(), 1);
        verify(songDao).findById(1L);
        verify(albumDao).findById(1L);
    }

    @Test
    public void testAddSongInException() {
        Song song = new Song();
        Album album = new Album();
        album.setSongsIn(new HashSet<>());
        album.getSongsIn().add(song);

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findById(anyLong())).thenReturn(album);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            albumService.addSongIn(1L, 1L);
        });

        assertEquals("Album already contains such a song", dataChangesException.getMessage());
        verify(songDao).findById(1L);
        verify(albumDao).findById(1L);
    }

    @Test
    public void testRemoveSavedAlbum() {
       Song song = new Song();
       Album album = new Album();
       album.setSongsIn(new HashSet<>());
       album.getSongsIn().add(song);

       when(songDao.findById(anyLong())).thenReturn(song);
       when(albumDao.findById(anyLong())).thenReturn(album);

       albumService.removeSongIn(1L, 1L);

       assertEquals(album.getSongsIn().size(), 0);
       verify(songDao).findById(1L);
       verify(albumDao).findById(1L);
   }

    @Test
    public void testRemoveSavedAlbumException() {
        Song song = new Song();
        Album album = new Album();
        album.setSongsIn(new HashSet<>());

        when(songDao.findById(anyLong())).thenReturn(song);
        when(albumDao.findById(anyLong())).thenReturn(album);

        DataChangesException dataChangesException = assertThrows(DataChangesException.class, () -> {
            albumService.removeSongIn(1L, 1L);
        });

        assertEquals("Album does not contain such a song", dataChangesException.getMessage());
        verify(songDao).findById(1L);
        verify(albumDao).findById(1L);
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
    public void findCreatedAlbumInfoDtoFromAccountIdTest() {
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
    public void findAllAlbumInfoDtoTest() {
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
    public void findAlbumInfoDtoByTitleTest() {
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
}