package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.Exceptions.DeletionIsNotPossibleException;
import senla.Exceptions.ObjectAlreadyExistsException;
import senla.Exceptions.ObjectNotFoundException;
import senla.models.Account;
import senla.util.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao {
    private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);
    private static final List<Account> accounts = new ArrayList<>();

    public Account getById(long id){
        Optional<Account> OA = accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if(OA.isEmpty()){
            String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        return OA.get();
    }

    public void add(Account account){
        accounts.add(account);
    }

    public void update(long id, String nickname, Role role){
        Account account = getById(id);
        account.setNickname(nickname);
        account.setRole(role);
    }

    public void addSongIdPerformed(long id, long songId){
        Account account = getById(id);

        if(account.getSongsIdPerformed().contains(songId)){
            String errorMassage = "Песня с индексом " + songId + " уже есть в списке исполненных";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        account.getSongsIdPerformed().add(songId);
    }

    public void removeSongIdPerformed(long id, long songId){
        Account account = getById(id);

        if(!account.getSongsIdPerformed().contains(songId)){
            String errorMassage = "Песни с индексом " + songId + " нет в списке исполненных";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        account.getSongsIdPerformed().remove(songId);
    }

    public void addCreatedAlbumId(long id, long albumId){
        Account account = getById(id);

        if(account.getCreatedAlbumsId().contains(albumId)){
            String errorMassage = "Альбом с индексом " + albumId + " уже есть в списке созданных";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        account.getCreatedAlbumsId().add(albumId);
    }

    public void removeCreatedAlbumId(long id, long albumId){
        Account account = getById(id);

        if(!account.getCreatedAlbumsId().contains(albumId)){
            String errorMassage = "Альбома с индексом " + albumId + " нет в списке созданных";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        account.getCreatedAlbumsId().remove(albumId);
    }

    public void addSavedAlbum(long id, long albumId){
        Account account = getById(id);

        if(account.getSavedAlbumsId().contains(albumId)){
            String errorMassage = "Альбом с индексом " + albumId + " уже есть в списке сохранённых";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        account.getSavedAlbumsId().add(albumId);
    }

    public void removeSavedAlbum(long id, long albumId){
        Account account = getById(id);

        if(!account.getSavedAlbumsId().contains(albumId)){
            String errorMassage = "Альбома с индексом " + albumId + " нет в списке сохранённых";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        account.getSavedAlbumsId().remove(albumId);
    }

    public void deleteById(long id){
        Account account = getById(id);
        if(account.getCreatedAlbumsId().size() != 0 || account.getSongsIdPerformed().size() != 0){
            String errorMassage = "Невозможно удалить аккаунт, существуют связи";
            logger.error(errorMassage);
            throw new DeletionIsNotPossibleException(errorMassage);
        }
        accounts.remove(account);
    }
}
