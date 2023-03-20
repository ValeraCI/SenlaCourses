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
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Location;
import senla.models.Song;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
@Transactional
public class SongDaoTest {
    @Autowired
    private SongDao songDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AccountDao accountDao;


    private Song createSong() {
        Song song = new Song();

        song.setTitle("TestSong");
        song.setGenre(genreDao.findById(2L));
        Location location = new Location(song, "./somePath");
        song.setLocation(location);
        song.setAuthors(new ArrayList(List.of(accountDao.findById(1L))));
        return song;
    }

    @Test
    public void testFindById() {
        Song song = songDao.findById(1L);

        assertEquals("Лиза", song.getTitle());
        assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void testFindByTitle() {
        List<Song> songs = songDao.findByTitle("Лиза", 0, 10);

        Song song = songs.stream().filter(s -> s.getId() == 1).findFirst().get();

        assertEquals("Лиза", song.getTitle());
        assertEquals(1L, song.getGenre().getId().longValue());
        assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void testFindByGenre() {
        List<Song> songs = songDao.findByGenre(genreDao.findById(1L), 0, 10);

        Song song = songs.stream().filter(s -> s.getId() == 1).findFirst().get();

        assertEquals("Лиза", song.getTitle());
        assertEquals(1L, song.getGenre().getId().longValue());
        assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void testFindByAlbumId() {
        List<Song> songs = songDao.findByAlbumId(3L);

        Song song = songs.stream().filter(s -> s.getId() == 1).findFirst().get();

        assertEquals("Лиза", song.getTitle());
        assertEquals(1L, song.getGenre().getId().longValue());
        assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void testFindAll() {
        List<Song> songs = songDao.findAll(0, 10);

        assertNotNull(songs);
        assertTrue(songs.size() <= 10);
    }

    @Test
    public void testSave() {
        Song song = createSong();

        Long index = songDao.save(song);
        song = songDao.findById(index);

        assertEquals("TestSong", song.getTitle());
        assertEquals(2L, song.getGenre().getId().longValue());
    }

    @Test
    public void testUpdate() {
        Song song = songDao.findById(2L);
        assertEquals("TEASER", song.getTitle());

        song.setTitle("TEASER TWO");
        songDao.update(song);

        song = songDao.findById(2L);
        assertEquals("TEASER TWO", song.getTitle());
    }

    @Test
    public void testDeleteById() {
        songDao.deleteById(5L);

        DataBaseWorkException dataBaseWorkException =
                assertThrows(DataBaseWorkException.class, () -> {
                    songDao.findById(5L);
                });

        assertEquals("No entity found for query", dataBaseWorkException.getMessage());
    }
}
