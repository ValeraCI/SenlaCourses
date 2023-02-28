package senla.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.models.Account;
import senla.models.Album;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlbumMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(AlbumCreateUpdateDataDto.class, Album.class)
                .addMappings(m -> m.skip(Album::setCreator))
                .addMappings(m -> m.skip(Album::setCreateDate))
                .setPostConverter(createAlbumDtoToAlbum());
    }

    public Converter<AlbumCreateUpdateDataDto, Album> createAlbumDtoToAlbum() {
        return context -> {
            Album destination = context.getDestination();
            mapAlbumSpecificFields(destination);
            return context.getDestination();
        };
    }

    public void mapAlbumSpecificFields(Album destination) {
        destination.setCreateDate(LocalDate.now());
    }

    public Album toEntity(AlbumCreateUpdateDataDto dto, Account account) {
        Album album = Objects.isNull(dto) ? null : mapper.map(dto, Album.class);
        album.setCreator(account);

        return album;
    }

    public AlbumInfoDto toAlbumInfoDto(Album entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AlbumInfoDto.class);
    }

    public List<AlbumInfoDto> toAlbumInfoDtoList(List<Album> albums) {
        List<AlbumInfoDto> albumInfoDtoList = albums
                .stream()
                .map(album -> Objects.isNull(album) ? null : mapper.map(album, AlbumInfoDto.class))
                .collect(Collectors.toList());

        return albumInfoDtoList;
    }
}
