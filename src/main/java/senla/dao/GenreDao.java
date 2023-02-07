package senla.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Genre;

@Repository
public class GenreDao extends AbstractDao<Genre, Long> {
    public GenreDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Genre.class, entityManager, criteriaBuilder);
    }
}
