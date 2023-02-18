package senla.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Genre;
import senla.models.GenreTitle;
import senla.models.Genre_;

@Repository
public class GenreDao extends AbstractDao<Genre, Long> {
    public GenreDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Genre.class, entityManager, criteriaBuilder);
    }

    public Genre findByTitle(String genreTitle){
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
        Root<Genre> root = criteriaQuery.from(typeParameterClass);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder
                        .equal(root.get(Genre_.GENRE_TITLE), GenreTitle.valueOf(genreTitle))
                );

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
