package senla.daoTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.TestConfiguration.TestConfiguration;
import senla.dao.GenreDao;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {TestConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@WebAppConfiguration
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void testGenreDao(){
        String[] programGenres = new String[]{
            "POP", "RAP", "BLUES", "CHANSON", "CLASSICAL", "DISCO", "ELECTRONIC", "JAZZ", "MUSICAL", "RENAISSANCE",
                "ROCK", "SPIRITUAL"
        };
        String daoGenres[] = new String[programGenres.length];
        for(int i = 0; i < daoGenres.length; i++){
            daoGenres[i] = String.valueOf(genreDao.findById(i+1L).getGenreTitle());
        }
        Assert.assertArrayEquals(programGenres, daoGenres);
    }

    @Test
    public void findByTitleTest(){
        String searchParam = "POP";
        String genreTitle = String.valueOf(genreDao.findByTitle(searchParam).getGenreTitle());

        Assert.assertEquals(genreTitle, searchParam);

    }
}
