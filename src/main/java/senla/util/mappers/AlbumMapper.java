package senla.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senla.dto.album.AlbumInfoDto;
import senla.dto.album.CreateAlbumDto;
import senla.models.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AlbumMapper {
    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(CreateAlbumDto.class, Album.class)
                .addMappings(m -> m.skip(Album::setCreator))
                .addMappings(m -> m.skip(Album::setCreateDate))
                .setPostConverter(
                        createAlbumDtoToAlbum());

    }

    public Converter<CreateAlbumDto, Album> createAlbumDtoToAlbum() {
        return context -> {
            CreateAlbumDto source = context.getSource();
            Album destination = context.getDestination();
            mapAlbumSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapAlbumSpecificFields(CreateAlbumDto source, Album destination) {
        destination.setCreateDate(LocalDate.now());
    }

    public Album toEntity(CreateAlbumDto dto, Account account) {
        Album album = Objects.isNull(dto) ? null : mapper.map(dto, Album.class);
        album.setCreator(account);

        return album;
    }

    public AlbumInfoDto toAlbumInfoDto(Album entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AlbumInfoDto.class);
    }

    public List<AlbumInfoDto> toAlbumInfoDtoList(List<Album> albums){
        List<AlbumInfoDto> albumInfoDtoList = new ArrayList<>();
        for(Album album: albums){
            albumInfoDtoList.add(
                    Objects.isNull(album) ? null : mapper.map(album, AlbumInfoDto.class)
            );
        }
        return albumInfoDtoList;
    }
}
