package senla.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.*;
import java.util.List;

@Repository
public class AlbumDao extends AbstractDao<Album, Long> {

    public AlbumDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Album.class, entityManager, criteriaBuilder);
    }

    public List<Album> findByTitle(String title) {
        try {
            CriteriaQuery<Album> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
            Root<Album> root = criteriaQuery.from(typeParameterClass);
            root.fetch(Album_.SONGS_IN, JoinType.LEFT);

            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder
                            .like(root.get(Album_.TITLE), "%" + title + "%")
                    );

            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public List<Album> findSavedFromByAccountId(Long id){
        try {
            CriteriaQuery<Album> query = criteriaBuilder.createQuery(typeParameterClass);
            Root<Album> root = query.from(typeParameterClass);
            Join<Account, Album> join = root.join(Album_.SAVED_FROM, JoinType.LEFT);

            query.select(query.from(typeParameterClass))
                    .where(criteriaBuilder.equal(join.get(Account_.ID), id));

            return entityManager.createQuery(query).getResultList();
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public List<Album> findCreatedFromAccountId(Long id){
        try {
            CriteriaQuery<Album> query = criteriaBuilder.createQuery(typeParameterClass);
            Root<Album> root = query.from(typeParameterClass);
            Join<Account, Album> join = root.join(Album_.CREATOR, JoinType.LEFT);

            query.select(root)
                    .where(criteriaBuilder.equal(join.get(Account_.ID), id));

            return entityManager.createQuery(query).getResultList();
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }
}
