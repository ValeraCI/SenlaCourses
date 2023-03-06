package senla.test.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.WebMvcConfig;
import senla.dao.GenreDao;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
@Transactional
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void testGenreDao() {
        String[] programGenres = new String[]{
                "POP", "RAP", "BLUES", "CHANSON", "CLASSICAL", "DISCO", "ELECTRONIC", "JAZZ", "MUSICAL", "RENAISSANCE",
                "ROCK", "SPIRITUAL"
        };
        String daoGenres[] = new String[programGenres.length];
        for (int i = 0; i < daoGenres.length; i++) {
            daoGenres[i] = String.valueOf(genreDao.findById(i + 1L).getGenreTitle());
        }

        assertArrayEquals(programGenres, daoGenres);
    }

    @Test
    public void testFindByTitle() {
        String searchParam = "POP";
        String genreTitle = String.valueOf(genreDao.findByTitle(searchParam).getGenreTitle());

        assertEquals(genreTitle, searchParam);
    }
}