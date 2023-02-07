package senla.dao;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Song;

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
}
