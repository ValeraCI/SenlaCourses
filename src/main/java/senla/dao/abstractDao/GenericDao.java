package senla.dao.abstractDao;

import senla.models.AEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends AEntity, PK extends Serializable>{
    PK save(T entity);
    void update(T entity);
    void deleteById(PK id);
    List<T> findAll();
    T findById(PK id);
}
