package senla.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Location;
import senla.models.Song;
import senla.test.configuration.WebMvcConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {WebMvcConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class SongDaoTest {
    @Autowired
    private SongDao songDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AccountDao accountDao;


    private Song createSong(){
        Song song = new Song();

        song.setTitle("TestSong");
        song.setGenre(genreDao.findById(2L));
        Location location = new Location(song, "./somePath");
        song.setLocation(location);
        song.setAuthors(new ArrayList(Arrays.asList(accountDao.findById(1L))));
        return song;
    }

    @Test
    public void findByIdTest(){
        Song song = songDao.findById(1L);

        Assert.assertEquals("Лиза", song.getTitle());
        Assert.assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void findByTitleTest(){
        List<Song> songs = songDao.findByTitle("Лиза");

        Song song = songs.stream().filter(s -> s.getId() == 1).findFirst().get();

        Assert.assertEquals("Лиза", song.getTitle());
        Assert.assertEquals(1L, song.getGenre().getId().longValue());
        Assert.assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void findByGenreTest(){
        List<Song> songs = songDao.findByGenre(genreDao.findById(1L));

        Song song = songs.stream().filter(s -> s.getId() == 1).findFirst().get();

        Assert.assertEquals("Лиза", song.getTitle());
        Assert.assertEquals(1L, song.getGenre().getId().longValue());
        Assert.assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void findByAlbumIdTest(){
        List<Song> songs = songDao.findByAlbumId(3L);

        Song song = songs.stream().filter(s -> s.getId() == 1).findFirst().get();

        Assert.assertEquals("Лиза", song.getTitle());
        Assert.assertEquals(1L, song.getGenre().getId().longValue());
        Assert.assertEquals(1L, song.getId().longValue());
    }

    @Test
    public void findAllTest(){
        List<Song> songs = songDao.findAll();

        for(int i = 0; i < songs.size(); i++){
            Assert.assertEquals(songs.get(i).getTitle(), songDao.findById(i+1L).getTitle());
        }
    }

    @Test
    public void saveTest(){
        Song song = createSong();

        Long index = songDao.save(song);
        song = songDao.findById(index);

        Assert.assertEquals("TestSong", song.getTitle());
        Assert.assertEquals(2L, song.getGenre().getId().longValue());
    }

    @Test
    public void updateTest(){
        Song song = songDao.findById(2L);
        Assert.assertEquals("TEASER", song.getTitle());

        song.setTitle("TEASER TWO");
        songDao.update(song);

        song = songDao.findById(2L);
        Assert.assertEquals("TEASER TWO", song.getTitle());
    }

    @Test(expected = DataBaseWorkException.class)
    public void deleteByIdTest(){
        songDao.deleteById(5L);
        Song song = songDao.findById(5L);
        System.out.println(song.getTitle());
    }
}
