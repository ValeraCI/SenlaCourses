package senla.dao;


import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Album;
import senla.models.Album_;
import senla.models.Genre;
import senla.models.Song;
import senla.models.Song_;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
public class SongDao extends AbstractDao<Song, Long> {

    public SongDao() {
        super(Song.class);
    }

    @Override
    public Song findById(Long id) {
        try {
            EntityGraph graph = entityManager.getEntityGraph("song-authors-entity-graph");
            Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", graph);
            Song song = entityManager.find(Song.class, id, properties);

            if (song == null) {
                throw new DataBaseWorkException();
            }

            return song;
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }

    public List<Song> findByGenre(Genre genre) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

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
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }

    public List<Song> findByTitle(String title) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(typeParameterClass);
            Root<Song> root = criteriaQuery.from(typeParameterClass);
            root.fetch(Song_.AUTHORS, JoinType.LEFT);
            root.fetch(Song_.GENRE);

            criteriaQuery
                    .select(root)
                    .where(criteriaBuilder.like(root.get(Song_.TITLE), "%" + title + "%"));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }

    public List<Song> findByAlbumId(Long albumId) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Song> query = criteriaBuilder.createQuery(Song.class);

            Root<Song> root = query.from(typeParameterClass);
            Join<Album, Song> join = root.join(Song_.CONTAINED_IN, JoinType.LEFT);

            query.select(root)
                    .where(criteriaBuilder.equal(join.get(Album_.ID), albumId));

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }
}
