package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.annotations.Loggable;
import senla.dto.album.AlbumCreateDto;
import senla.services.AlbumService;
import senla.util.Json;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final Json json;

    @Loggable
    public void save(String jsonAlbumDto){
        AlbumCreateDto albumDto = json.deserialize(jsonAlbumDto, AlbumCreateDto.class);
        albumService.save(albumDto);
    }

    @Loggable
    public void remove(Long id){
        albumService.deleteById(id);
    }

    @Loggable
    public String getById(Long id){
        return json.serialize(albumService.findAlbumInfoDtoById(id));
    }

    @Loggable
    public void addSongIn(Long albumId, Long songId){
        albumService.addSongIn(albumId, songId);
    }

    @Loggable
    public void removeSongIn(Long albumId, Long songId){
        albumService.removeSongIn(albumId, songId);
    }
}
