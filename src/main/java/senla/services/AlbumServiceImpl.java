package senla.services;

import org.apache.commons.math3.stat.inference.MannWhitneyUTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dao.AccountDao;
import senla.dao.AlbumDao;
import senla.dao.SongDao;
import senla.dto.album.AlbumCreateDto;
import senla.dto.album.AlbumInfoDto;
import senla.dto.album.AlbumUpdateDto;
import senla.exceptions.DataChangesException;
import senla.exceptions.InsufficientRightsException;
import senla.models.AEntity;
import senla.models.Account;
import senla.models.AccountDetails;
import senla.models.Album;
import senla.models.RoleTitle;
import senla.models.Song;
import senla.services.api.AlbumService;
import senla.util.Convertor;
import senla.util.Paginator;
import senla.util.Unpacker;
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
    public Long save(AlbumCreateDto albumDto) {
        Account account = accountDao.findById(albumDto.getCreatorId());

        Album album = albumMapper.toEntity(albumDto, account);

        return albumDao.save(album);
    }


    @Override
    public void updateData(Long id, AlbumUpdateDto albumDto, AccountDetails accountDetails) {
        Album album = albumDao.findByIdWithCreator(id);

        if (!hasAccess(album, accountDetails)) {
            throw new InsufficientRightsException("You can't delete this album");
        }

        album.setTitle(albumDto.getTitle());
        albumDao.update(album);
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
    public List<AlbumInfoDto> findAllAlbumInfoDto(String pageNumber, String limit) {
        Integer pageNumberInteger = Convertor.stringToInteger(pageNumber);
        Integer limitInteger = Convertor.stringToInteger(limit);

        limitInteger = Paginator.limitingMinimumValueToOne(limitInteger);

        Long totalCount = albumDao.getTotalCount();
        Integer firstResult = Paginator.getFirstElement(pageNumberInteger, totalCount, limitInteger);


        return albumMapper.toAlbumInfoDtoList(
                albumDao.findAll(Math.toIntExact(firstResult), limitInteger)
        );
    }

    @Override
    public List<AlbumInfoDto> findAlbumInfoDtoByTitle(String title, String pageNumber, String limit) {
        Integer pageNumberInteger = Convertor.stringToInteger(pageNumber);
        Integer limitInteger = Convertor.stringToInteger(limit);

        limitInteger = Paginator.limitingMinimumValueToOne(limitInteger);

        Long totalCount = albumDao.getTotalCount();
        Integer firstResult = Paginator.getFirstElement(pageNumberInteger, totalCount, limitInteger);


        return albumMapper.toAlbumInfoDtoList(
                albumDao.findByTitle(title, Math.toIntExact(firstResult), limitInteger)
        );
    }

    @Override
    public List<AlbumInfoDto> findRecommendedFor(AccountDetails accountDetails, String limit) {
        Integer limitInteger = Convertor.stringToInteger(limit);

        limitInteger = Paginator.limitingMinimumValueToOne(limitInteger);

        Account account = accountDao.findWithSavedAlbumsById(accountDetails.getId());

        Double[] savedAlbums = getAccountSavedAlbums(account);

        Set<Long> recommendation;
        if (savedAlbums.length < 3) {
            recommendation = getRandomRecommendation(savedAlbums, limitInteger);
        } else {
            recommendation = getRecommendation(savedAlbums, limitInteger);
        }

        return albumMapper.toAlbumInfoDtoList(albumDao.findByIds(recommendation));
    }

    private boolean hasAccess(Album album, AccountDetails accountDetails) {
        return album.getCreator().getId().equals(accountDetails.getId()) ||
                accountDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .anyMatch(auth -> auth.equals(RoleTitle.ROLE_ADMINISTRATOR.toString())
                                || auth.equals(RoleTitle.ROLE_OWNER.toString()));
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

        double[] savedAlbumsArr = Unpacker.convertToPrimitiveDoubleArray(savedAlbums);

        while (recommendation.size() < limit) {
            List<Account> accounts = accountDao.findWithSavedAlbumsByIdInBetween(id, id + numberOfUsersAtTime);
            id += numberOfUsersAtTime;

            if (accounts.size() == 0) {
                break;
            }

            for (Account account : accounts) {
                double[] accountSavedAlbumsArr = getAccountPrimitiveSavedAlbums(account);

                if (isSimilar(savedAlbumsArr, accountSavedAlbumsArr)) {
                    addRecommendedAlbums(savedAlbums, recommendation, accountSavedAlbumsArr, limit);
                }
            }
        }

        return recommendation;
    }

    private double[] getAccountPrimitiveSavedAlbums(Account account) {
        return account.getSavedAlbums()
                .stream()
                .map(AEntity::getId)
                .mapToDouble(Long::doubleValue)
                .toArray();
    }

    private Double[] getAccountSavedAlbums(Account account) {
        return account.getSavedAlbums()
                .stream()
                .map(album -> album.getId().doubleValue())
                .toArray(Double[]::new);
    }

    private boolean isSimilar(double[] savedAlbumsArr, double[] accountSavedAlbumsArr) {
        return mannWhitneyUTest.mannWhitneyUTest(savedAlbumsArr, accountSavedAlbumsArr) >= percentageOfSimilarity;
    }

    private void addRecommendedAlbums(Double[] savedAlbums, Set<Long> recommendation, double[] accountSavedAlbumsArr, Integer limit) {
        Arrays.stream(accountSavedAlbumsArr)
                .filter(albumId -> !Arrays.asList(savedAlbums).contains(albumId))
                .limit(limit - recommendation.size())
                .forEach(albumId -> recommendation.add((long) albumId));
    }
}
