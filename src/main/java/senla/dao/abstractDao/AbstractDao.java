package senla.dao.abstractDao;

import lombok.AllArgsConstructor;
import senla.exceptions.DataBaseWorkException;
import senla.models.AEntity;
import senla.models.AEntity_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public abstract class AbstractDao<T extends AEntity, PK extends Serializable> implements GenericDao<T, Long> {

    protected final Class<T> typeParameterClass;
    @PersistenceContext
    protected final EntityManager entityManager;

    protected final CriteriaBuilder criteriaBuilder;

    @Override
    public Long save(T entity) {
        try {
            entityManager.persist(entity);
            return entity.getId();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public void update(T entity) {
        try {
            entityManager.merge(entity);
            entityManager.flush();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(typeParameterClass);
            Root<T> root = criteriaDelete.from(typeParameterClass);
            criteriaDelete.where(criteriaBuilder.equal(root.get(AEntity_.ID), id));
            entityManager.createQuery(criteriaDelete).executeUpdate();
            entityManager.flush();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);

            criteriaQuery.select(criteriaQuery.from(typeParameterClass));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public T findById(Long id) {
        try {
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
            Root<T> root = criteriaQuery.from(typeParameterClass);

            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder
                            .equal(root.get(AEntity_.ID), id)
                    );

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }
}
