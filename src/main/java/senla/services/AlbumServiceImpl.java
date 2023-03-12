package senla.services;

import org.apache.commons.math3.stat.inference.MannWhitneyUTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.SongDao;
import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.exceptions.DataChangesException;
import senla.exceptions.InsufficientRightsException;
import senla.models.AEntity;
import senla.models.Account;
import senla.models.AccountDetails;
import senla.models.Album;
import senla.models.Song;
import senla.services.api.AlbumService;
import senla.util.mappers.AlbumMapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final Long numberOfUsersAtTime;
    private final Double percentageOfSimilarity;
    private final AlbumDao albumDao;
    private final AccountDao accountDao;
    private final SongDao songDao;
    private final AlbumMapper albumMapper;
    private final MannWhitneyUTest mannWhitneyUTest;

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao, AccountDao accountDao, SongDao songDao, AlbumMapper albumMapper,
                            MannWhitneyUTest mannWhitneyUTest,
                            @Value("${recommendation.numberOfUsersAtTime}") Long numberOfUsersAtTime,
                            @Value("${recommendation.percentageOfSimilarity}") Double percentageOfSimilarity) {
        this.albumDao = albumDao;
        this.accountDao = accountDao;
        this.songDao = songDao;
        this.albumMapper = albumMapper;
        this.mannWhitneyUTest = mannWhitneyUTest;
        this.numberOfUsersAtTime = numberOfUsersAtTime;
        this.percentageOfSimilarity = percentageOfSimilarity;
    }

    @Override
    public Long save(AlbumCreateUpdateDataDto albumDto) {
        Account account = accountDao.findById(albumDto.getCreatorId());

        Album album = albumMapper.toEntity(albumDto, account);

        return albumDao.save(album);
    }

    @Override
    public AlbumInfoDto findAlbumInfoDtoById(Long id) {
        Album album = albumDao.findById(id);
        return albumMapper.toAlbumInfoDto(album);
    }

    @Override
    public void deleteById(Long id, AccountDetails accountDetails) {
        Album album = albumDao.findByIdWithCreator(id);

        if (!hasAccess(album, accountDetails)) {
            throw new InsufficientRightsException("You can't delete this album");
        }

        albumDao.deleteById(id);
    }

    @Override
    public void addSongIn(Long albumId, Long songId, AccountDetails accountDetails) {
        Song song = songDao.findById(songId);
        Album album = albumDao.findByIdWithCreator(albumId);

        if (!hasAccess(album, accountDetails)) {
            throw new InsufficientRightsException("You can't add a song to this album");
        }

        if (!album.getSongsIn().contains(song)) {
            album.getSongsIn().add(song);
        } else {
            throw new DataChangesException("Album already contains such a song");
        }
    }

    @Override
    public void removeSongIn(Long albumId, Long songId, AccountDetails accountDetails) {
        Song song = songDao.findById(songId);
        Album album = albumDao.findByIdWithCreator(albumId);

        if (!hasAccess(album, accountDetails)) {
            throw new InsufficientRightsException("You can't delete a song from this album");
        }

        if (album.getSongsIn().contains(song)) {
            album.getSongsIn().remove(song);
        } else {
            throw new DataChangesException("Album does not contain such a song");
        }
    }

    @Override
    public List<AlbumInfoDto> findSavedAlbumsInfoDtoFromAccountId(Long accountId) {
        return albumMapper.toAlbumInfoDtoList(
                albumDao.findSavedFromByAccountId(accountId)
        );
    }

    @Override
    public List<AlbumInfoDto> findCreatedAlbumInfoDtoFromAccountId(Long accountId) {
        return albumMapper.toAlbumInfoDtoList(
                albumDao.findCreatedFromAccountId(accountId)
        );
    }

    @Override
    public List<AlbumInfoDto> findAllAlbumInfoDto() {
        return albumMapper.toAlbumInfoDtoList(
                albumDao.findAll()
        );
    }

    @Override
    public List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title) {
        return albumMapper.toAlbumInfoDtoList(
                albumDao.findByTitle(title)
        );
    }

    @Override
    public List<AlbumInfoDto> findRecommendedFor(AccountDetails accountDetails, Integer limit) {
        Double[] savedAlbums =
                accountDao.findWithSavedAlbumsById(accountDetails.getId()).getSavedAlbums()
                        .stream()
                        .map(album -> album.getId().doubleValue())
                        .toArray(Double[]::new);

        Set<Long> recommendation;
        if (savedAlbums.length < 3) {
            recommendation = getRandomRecommendation(savedAlbums, limit);
        } else {
            recommendation = getRecommendation(savedAlbums, limit);
        }

        return albumMapper.toAlbumInfoDtoList(albumDao.findByIds(recommendation));
    }

    private boolean hasAccess(Album album, AccountDetails accountDetails) {
        return album.getCreator().getId().equals(accountDetails.getId()) ||
                accountDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(auth -> auth.equals("ADMINISTRATOR")
                                || auth.equals("OWNER"));
    }

    private Set<Long> getRandomRecommendation(Double[] savedAlbums, Integer limit) {
        Set<Long> savedAlbumsLong = Arrays.stream(savedAlbums)
                .map(Double::longValue)
                .collect(Collectors.toSet());

        return albumDao.findRandomExcept(limit, savedAlbumsLong)
                .stream()
                .map(AEntity::getId)
                .collect(Collectors.toSet());
    }

    private Set<Long> getRecommendation(Double[] savedAlbums, Integer limit) {
        Set<Long> recommendation = new HashSet<>();

        Long id = 1L;

        double[] savedAlbumsArr = Arrays.stream(savedAlbums)
                .mapToDouble(Double::doubleValue)
                .toArray();

        while (recommendation.size() < limit) {
            List<Account> accounts = accountDao.findWithSavedAlbumsByIdInBetween(id, id + numberOfUsersAtTime);
            id += numberOfUsersAtTime;

            if (accounts.size() == 0) {
                break;
            }

            for (Account account : accounts) {
                double[] accountSavedAlbumsArr = account.getSavedAlbums()
                        .stream()
                        .map(AEntity::getId)
                        .mapToDouble(Long::doubleValue)
                        .toArray();

                if (mannWhitneyUTest
                        .mannWhitneyUTest(savedAlbumsArr, accountSavedAlbumsArr) >= percentageOfSimilarity) {

                    Arrays.stream(accountSavedAlbumsArr)
                            .filter(albumId -> !Arrays.asList(savedAlbums).contains(albumId))
                            .limit(limit - recommendation.size())
                            .forEach(albumId -> recommendation.add((long) albumId));
                }
            }
        }

        return recommendation;
    }

}
