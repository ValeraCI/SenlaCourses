package senla.dao;

import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Genre;
import senla.models.GenreTitle;
import senla.models.Genre_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class GenreDao extends AbstractDao<Genre, Long> {
    public GenreDao() {
        super(Genre.class);
    }

    public Genre findByTitle(String genreTitle) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

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
