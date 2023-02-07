package senla.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.*;

import java.util.List;
import java.util.Set;

@Repository
public class AccountDao extends AbstractDao<Account, Long> {

    public AccountDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Account.class, entityManager, criteriaBuilder);
    }

    @Override
    public Long save(Account entity) {
        try {
            entityManager.persist(entity);
            return entity.getId();
        } catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public Account findByEmail(String email){
        try {
            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);

            Root<Account> root = query.from(typeParameterClass);
            Join<Account, LoginDetails> join = root.join(Account_.LOGIN_DETAILS);
            root.fetch(Account_.ROLE, JoinType.INNER);
            query
                    .select(root)
                    .where(criteriaBuilder
                            .equal(join.get(LoginDetails_.EMAIL), email)
                    );

            return entityManager.createQuery(query).getSingleResult();
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public void update(Account entity) {
        try {
            CriteriaUpdate<Account> criteriaAccountUpdate =
                    criteriaBuilder.createCriteriaUpdate(typeParameterClass);
            Root<Account> rootAccount = criteriaAccountUpdate.from(typeParameterClass);

            criteriaAccountUpdate
                    .set(rootAccount.get(Account_.NICKNAME), entity.getNickname())
                    .set(rootAccount.get(Account_.ROLE), entity.getRole())
                    .where(criteriaBuilder.equal(rootAccount.get(AEntity_.ID), entity.getId()));

            entityManager.createQuery(criteriaAccountUpdate).executeUpdate();
        } catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public void addSavedAlbum(Long accountId, Album album){
        try {
            //TODO вынести в отдельный метод
            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);

            Root<Account> root = query.from(typeParameterClass);
            root.fetch(Account_.SAVED_ALBUMS);
            query
                    .select(root)
                    .where(criteriaBuilder
                            .equal(root.get(Account_.ID), accountId)
                    );

            Account account = entityManager.createQuery(query).getSingleResult();

            if(!account.getSavedAlbums().contains(album)) {
                account.getSavedAlbums().add(album);
            }
            else{
                throw new RuntimeException("У пользователя этот альбом уже сохранён");
            }
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public void removeSavedAlbum(Long accountId, Album album){
        try {
            //TODO вынести в отдельный метод
            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);

            Root<Account> root = query.from(typeParameterClass);
            root.fetch(Account_.SAVED_ALBUMS);
            query
                    .select(root)
                    .where(criteriaBuilder
                            .equal(root.get(Account_.ID), accountId)
                    );

            Account account = entityManager.createQuery(query).getSingleResult();

            if(account.getSavedAlbums().contains(album)) {
                account.getSavedAlbums().remove(album);
            }
            else{
                throw new RuntimeException("У пользователя этот альбом не сохранён");
            }
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public Set<Album> findSavedAlbumsById(Long id){
        try {
            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);
            Root<Account> root = query.from(typeParameterClass);
            root.fetch(Account_.SAVED_ALBUMS);

            query.select(root)
                    .where(criteriaBuilder.equal(root.get(Account_.ID), id));

            return entityManager.createQuery(query).getSingleResult().getSavedAlbums();
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public Set<Album> findCreatedAlbumsById(Long id){
        try {
            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);
            Root<Account> root = query.from(typeParameterClass);
            root.fetch(Account_.CREATED_ALBUMS);

            query.select(root)
                    .where(criteriaBuilder.equal(root.get(Account_.ID), id));

            return entityManager.createQuery(query).getSingleResult().getCreatedAlbums();
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }
}
