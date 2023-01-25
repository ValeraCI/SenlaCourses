package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import senla.dto.AlbumDto;
import senla.services.AlbumService;
import senla.util.Json;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    private final AlbumService albumService;
    private final Json json;

    public void add(String jsonAlbumDto){
        AlbumDto albumDto = json.deserialize(jsonAlbumDto, AlbumDto.class);
        albumService.add(albumDto);
        logger.info("Добавление альбома c индексом {} произошло успешно", albumDto.getId());
    }

    public void remove(long id){
        albumService.deleteById(id);
        logger.info("Удаление альбома c индексом {} произошло успешно", id);
    }

    public String getById(long id){
        return json.serialize(albumService.getAlbumDtoById(id));
    }

    public void addSongIdIn(long id, long songId){
        albumService.addSongIdIn(id, songId);
        logger.info("Песня c индексом {} успешно добавлена в альбом с индексом {}", songId, id);
    }

    public void removeSongIdIn(long id, long songId){
        albumService.removeSongIdIn(id, songId);
        logger.info("Песня c индексом {} успешно удалена из альбома с индексом {}", songId, id);
    }
}
