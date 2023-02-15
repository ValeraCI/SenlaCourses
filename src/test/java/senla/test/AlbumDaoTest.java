package senla.test;

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
import senla.dao.AlbumDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Album;
import senla.test.configuration.Application;

import java.time.LocalDate;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class AlbumDaoTest {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AccountDao accountDao;

    private Album createAlbum(){
        Album album = new Album();

        album.setTitle("TestAlbum");
        album.setCreateDate(LocalDate.now());
        album.setCreator(accountDao.findById(1L));

        return album;
    }

    @Test
    public void findByIdTest(){
        Album album = albumDao.findById(1L);

        Assert.assertEquals("?", album.getTitle());
        Assert.assertEquals(1L, album.getId());
    }

    @Test
    public void findByTitleTest(){
        List<Album> albums = albumDao.findByTitle("?");

        Album album = albums.stream().filter(a -> a.getId() == 1).findFirst().get();

        Assert.assertEquals("?", album.getTitle());
        Assert.assertEquals(7L, album.getCreator().getId());
        Assert.assertEquals(1L, album.getId());
    }

    @Test
    public void findAllTest(){
        List<Album> albums = albumDao.findAll();

        for(int i = 0; i < albums.size(); i++){
            Assert.assertEquals(albums.get(i).getTitle(), albumDao.findById(i+1L).getTitle());
        }
    }

    @Test
    public void saveTest(){
        Album album = createAlbum();

        Long index = albumDao.save(album);
        album = albumDao.findById(index);

        Assert.assertEquals("TestAlbum", album.getTitle());
        Assert.assertEquals(1L, album.getCreator().getId());
    }

    @Test
    public void updateTest(){
        Album album = albumDao.findById(2L);
        Assert.assertEquals("LAST ONE", album.getTitle());

        album.setTitle("LAST TWO");
        albumDao.update(album);

        album = albumDao.findById(2L);
        Assert.assertEquals("LAST TWO", album.getTitle());
    }

    @Test(expected = DataBaseWorkException.class)
    public void deleteByIdTest(){
        albumDao.deleteById(4L);
        Album album = albumDao.findById(4L);
    }

    @Test
    public void findSavedFromByAccountIdTest(){
        albumDao.findSavedFromByAccountId(1L).stream().forEach(System.out::println);
    }

    @Test
    public void findSavedFromByAccountId(){
        albumDao.findSavedFromByAccountId(1L).stream().forEach(System.out::println);
    }

}
