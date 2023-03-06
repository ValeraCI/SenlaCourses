package senla.dao;

import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Account;
import senla.models.Account_;
import senla.models.Album;
import senla.models.Album_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AlbumDao extends AbstractDao<Album, Long> {

    public AlbumDao() {
        super(Album.class);
    }

    public List<Album> findByTitle(String title) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Album> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
            Root<Album> root = criteriaQuery.from(typeParameterClass);
            root.fetch(Album_.SONGS_IN, JoinType.LEFT);

            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder
                            .like(root.get(Album_.TITLE), "%" + title + "%")
                    );

            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataBaseWorkException(e.getMessage(), e);
        }
    }

    public List<Album> findSavedFromByAccountId(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Album> query = criteriaBuilder.createQuery(typeParameterClass);
            Root<Album> root = query.from(typeParameterClass);
            Join<Account, Album> join = root.join(Album_.SAVED_FROM);

            query.select(root)
                    .where(criteriaBuilder.equal(join.get(Account_.ID), id));

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataBaseWorkException(e.getMessage(), e);
        }
    }

    public List<Album> findCreatedFromAccountId(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Album> query = criteriaBuilder.createQuery(typeParameterClass);
            Root<Album> root = query.from(typeParameterClass);
            Join<Account, Album> join = root.join(Album_.CREATOR, JoinType.LEFT);

            query.select(root)
                    .where(criteriaBuilder.equal(join.get(Account_.ID), id));

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataBaseWorkException(e.getMessage(), e);
        }
    }
}
