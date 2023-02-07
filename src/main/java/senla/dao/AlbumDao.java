package senla.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.*;

import java.util.List;
import java.util.Map;

@Repository
public class AlbumDao extends AbstractDao<Album, Long> {

    public AlbumDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Album.class, entityManager, criteriaBuilder);
    }

    public void addSongIn(Long albumId, Song song){
       try {
           EntityGraph graph = entityManager.getEntityGraph("album-songsIn-entity-graph");
           Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
           Album album = entityManager.find(Album.class, albumId, properties);

           if(!album.getSongsIn().contains(song)){
               album.getSongsIn().add(song);
           }
           else {
               throw new RuntimeException("Альбом уже содержит такую песню"); //TODO написать свою ошибку
           }
       }catch (Exception e){
           throw new DataBaseWorkException(e);
       }
    }

    public void removeSongIn(Long albumId, Song song){
        try {
            EntityGraph graph = entityManager.getEntityGraph("album-songsIn-entity-graph");
            Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
            Album album = entityManager.find(Album.class, albumId, properties);

            if(album.getSongsIn().contains(song)){
                album.getSongsIn().remove(song);
            }
            else {
                throw new RuntimeException("Альбом не содержит такую песню"); //TODO написать свою ошибку
            }
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public List<Song> getSongsIn(Long albumId){
        CriteriaQuery<Song> query = criteriaBuilder.createQuery(Song.class);

        Root<Album> root = query.from(typeParameterClass);
        Join<Album, Song> join = root.join(Album_.songsIn);
        join.fetch(Song_.AUTHORS);

        query.select(join)
                .where(criteriaBuilder.equal(root.get(Album_.ID), albumId));

        return entityManager.createQuery(query).getResultList();
    }

    /*public List<Song> getSongsIn(Long albumId){
        EntityGraph graph = entityManager.getEntityGraph("album-songsIn-entity-graph");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
        Album album = entityManager.find(Album.class, albumId, properties);
        return album.getSongsIn();
    }
    Не нашёл как с помощью графа загрузить ещё владельцев песен. Возможно тут стоило реализовывать через
    song, но у этого класса нет ссылки на альбомы, в который он содержится
    */
}
