package senla.test.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.WebMvcConfig;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Album;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
@Transactional
public class AlbumDaoTest {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AccountDao accountDao;

    private Album createAlbum() {
        Album album = new Album();

        album.setTitle("TestAlbum");
        album.setCreateDate(LocalDate.now());
        album.setCreator(accountDao.findById(1L));

        return album;
    }

    @Test
    public void testFindById() {
        Album album = albumDao.findById(1L);

        assertEquals("?", album.getTitle());
        assertEquals(1L, album.getId().longValue());
    }

    @Test
    public void testFindByTitle() {
        List<Album> albums = albumDao.findByTitle("?");

        Album album = albums.stream().filter(a -> a.getId() == 1).findFirst().get();

        assertEquals("?", album.getTitle());
        assertEquals(7L, album.getCreator().getId().longValue());
        assertEquals(1L, album.getId().longValue());
    }

    @Test
    public void testFindAll() {
        List<Album> albums = albumDao.findAll();

        assertNotNull(albums);
    }

    @Test
    public void testSave() {
        Album album = createAlbum();

        Long index = albumDao.save(album);
        album = albumDao.findById(index);

        assertEquals("TestAlbum", album.getTitle());
        assertEquals(1L, album.getCreator().getId().longValue());
    }

    @Test
    public void testUpdate() {
        Album album = albumDao.findById(2L);
        assertEquals("LAST ONE", album.getTitle());

        album.setTitle("LAST TWO");
        albumDao.update(album);

        album = albumDao.findById(2L);
        assertEquals("LAST TWO", album.getTitle());
    }

    @Test
    public void testDeleteById() {
        albumDao.deleteById(4L);

        DataBaseWorkException dataBaseWorkException =
                assertThrows(DataBaseWorkException.class, () -> {
                    albumDao.findById(4L);
                });

        assertEquals("No entity found for query", dataBaseWorkException.getMessage());
    }

    @Test
    public void testFindSavedFromByAccountId() {
        List<Album> albums = albumDao.findSavedFromByAccountId(2L);

        assertEquals(1L, albums.size());
        assertEquals("?", albums.get(0).getTitle());
    }

    @Test
    public void testFindCreatedFromAccountId() {
        List<Album> albums = albumDao.findCreatedFromAccountId(7L);

        assertEquals(1L, albums.size());
        assertEquals("?", albums.get(0).getTitle());
    }
}
