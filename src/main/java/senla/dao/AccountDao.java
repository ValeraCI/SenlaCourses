package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.exceptions.DeletionIsNotPossibleException;
import senla.exceptions.ObjectAlreadyExistsException;
import senla.exceptions.ObjectNotFoundException;
import senla.models.Account;
import senla.models.Album;
import senla.models.Song;
import senla.util.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao {
    private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);
    private static final List<Account> accounts = new ArrayList<>();

    public Optional<Account> getById(long id){
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    private Account getAccountById(long id){
        Optional<Account> accountOptional = getById(id);
        if(accountOptional.isEmpty()){
            String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }
        return accountOptional.get();
    }

    public void add(Account account){
        accounts.add(account);
    }

    public void update(long id, String nickname, Role role){
        Account account = getAccountById(id);

        account.setNickname(nickname);
        account.setRole(role);
    }

    public void addSongIdPerformed(long id, Song song){
        Account account = getAccountById(id);

        if(account.getSongsPerformed().contains(song)){
            String errorMassage = "Песня с индексом " + song.getId() + " уже есть в списке исполненных";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        account.getSongsPerformed().add(song);
    }

    public void removeSongIdPerformed(long id, Song song){
        Account account = getAccountById(id);

        if(!account.getSongsPerformed().contains(song)){
            String errorMassage = "Песни с индексом " + song.getId() + " нет в списке исполненных";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        account.getSongsPerformed().remove(song);
    }

    public void addCreatedAlbum(long id, Album album){
        Account account = getAccountById(id);

        if(account.getCreatedAlbums().contains(album)){
            String errorMassage = "Альбом с индексом " + album.getId() + " уже есть в списке созданных";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        account.getCreatedAlbums().add(album);
    }

    public void removeCreatedAlbum(long id, Album album){
        Account account = getAccountById(id);

        if(!account.getCreatedAlbums().contains(album)){
            String errorMassage = "Альбома с индексом " + album.getId() + " нет в списке созданных";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        account.getCreatedAlbums().remove(album);
    }

    public void addSavedAlbum(long id, Album album){
        Account account = getAccountById(id);

        if(account.getSavedAlbums().contains(album)){
            String errorMassage = "Альбом с индексом " + album.getId() + " уже есть в списке сохранённых";
            logger.error(errorMassage);
            throw new ObjectAlreadyExistsException(errorMassage);
        }

        account.getSavedAlbums().add(album);
    }

    public void removeSavedAlbum(long id, Album album){
        Account account = getAccountById(id);

        if(!account.getSavedAlbums().contains(album)){
            String errorMassage = "Альбома с индексом " + album.getId() + " нет в списке сохранённых";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }

        account.getSavedAlbums().remove(album);
    }

    public void delete(long id){
        Account account = getAccountById(id);

        if(account.getCreatedAlbums().size() != 0 || account.getSongsPerformed().size() != 0){
            String errorMassage = "Невозможно удалить аккаунт, существуют связи";
            logger.error(errorMassage);
            throw new DeletionIsNotPossibleException(errorMassage);
        }
        accounts.remove(account);
    }
}
