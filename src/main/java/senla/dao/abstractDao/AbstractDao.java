package senla.dao.abstractDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import senla.exceptions.DataBaseWorkException;
import senla.models.AEntity;
import senla.models.AEntity_;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractDao<T extends AEntity, PK extends Serializable> implements GenericDao<T, PK> {

    protected final Class<T> typeParameterClass;
    protected final EntityManager entityManager;

    public AbstractDao(Class<T> typeParameterClass, EntityManager entityManager) {
        this.typeParameterClass = typeParameterClass;
        this.entityManager = entityManager;
    }

    @Override
    public PK save(T entity) {
        try {

        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }

        return null;
    }

    @Override
    public void update(T entity) {
        try {
            entityManager.merge(entity);
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    //@Transactional
    public void deleteById(PK id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<T> criteriaDelete = builder.createCriteriaDelete(typeParameterClass);
            Root<T> root = criteriaDelete.getRoot();
            criteriaDelete.where(builder.equal(root.get(AEntity_.ID), id));

            entityManager.createQuery(criteriaDelete).executeUpdate();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);

            criteriaQuery.select(criteriaQuery.from(typeParameterClass));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public T findById(PK id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);
            Root<T> root = criteriaQuery.from(typeParameterClass);

            criteriaQuery
                    .select(root)
                    .where(builder
                            .equal(root.get(AEntity_.ID), id)
                    );

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }
}
