package senla.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.Account;
import senla.models.Genre;
import senla.models.Location;
import senla.models.Song;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SongMapper {
    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(SongCreateDto.class, Song.class)
                .addMappings(m -> m.skip(Song::setAuthors))
                .addMappings(m -> m.skip(Song::setGenre))
                .addMappings(m -> m.skip(Song::setLocation));

        mapper.createTypeMap(Song.class, SongInfoDto.class)
                .addMappings(m -> m.skip(SongInfoDto::setAuthorsNicknames))
                .setPostConverter(
                        songToSongInfoDtoConverter());
    }

    public Converter<Song, SongInfoDto> songToSongInfoDtoConverter() {
        return context -> {
            Song source = context.getSource();
            SongInfoDto destination = context.getDestination();
            mapSongInfoDtoSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSongInfoDtoSpecificFields(Song source, SongInfoDto destination) {
        List<String> authorsNicknames = new ArrayList<>();
        for (Account author: source.getAuthors()) {
            authorsNicknames.add(author.getNickname());
        }
        destination.setAuthorsNicknames(authorsNicknames);
    }

    public SongInfoDto toSongInfoDto(Song entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, SongInfoDto.class);
    }

    public Song toEntity(SongCreateDto dto, List<Account> authors, Genre genre, String path) {
        Song song = Objects.isNull(dto) ? null : mapper.map(dto, Song.class);
        song.setAuthors(authors);
        song.setGenre(genre);
        song.setLocation(new Location(song, path));

        return song;
    }

    public List<SongInfoDto> toSongInfoDtoList(List<Song> songs){
        List<SongInfoDto> songInfoDtoList = songs
                .stream()
                .map(song -> Objects.isNull(song) ? null : mapper.map(song, SongInfoDto.class))
                .collect(Collectors.toList());

        return songInfoDtoList;
    }
}
