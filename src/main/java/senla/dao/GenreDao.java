package senla.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
