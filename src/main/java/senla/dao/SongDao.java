package senla.dao;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Genre;
import senla.models.Song;
import senla.models.Song_;

import java.util.List;
import java.util.Map;

@Repository
public class SongDao extends AbstractDao<Song, Long> {

    public SongDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Song.class, entityManager, criteriaBuilder);
    }

    @Override
    public Song findById(Long id) {
        EntityGraph graph = entityManager.getEntityGraph("song-authors-entity-graph");
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
        Song song = entityManager.find(Song.class, id, properties);

        return song;
    }

    public List<Song> findByGenre(Genre genre) {
        try {
            CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
            Root<Song> root = criteriaQuery.from(typeParameterClass);
            root.fetch(Song_.AUTHORS);
            root.fetch(Song_.GENRE);

            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder
                            .equal(root.get(Song_.GENRE), genre)
                    );

            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    public List<Song> findByTitle(String title) {
        try {
            CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
            Root<Song> root = criteriaQuery.from(typeParameterClass);
            root.fetch(Song_.AUTHORS, JoinType.LEFT);
            root.fetch(Song_.GENRE);

            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder.like(root.get(Song_.TITLE), "%" + title + "%"));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public Long save(Song song) {
        try {
            entityManager.persist(song);
            entityManager.persist(song.getLocation());
            return song.getId();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }
}
