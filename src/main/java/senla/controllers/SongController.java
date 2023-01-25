package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import senla.dto.SongCreateDto;
import senla.services.SongService;
import senla.util.Json;

@Controller
@RequiredArgsConstructor
public class SongController {
    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    private final SongService songService;
    private final Json json;

    public void add(String jsonSongCreateDto){
        SongCreateDto songCreateDto = json.deserialize(jsonSongCreateDto, SongCreateDto.class);
        songService.add(songCreateDto);
        logger.info("Добавление песни c индексом {} произошло успешно", songCreateDto.getId());
    }

    public void remove(long id){
        songService.deleteById(id);
        logger.info("Удаление песни c индексом {} произошло успешно", id);
    }

    public String getById(long id){
        return json.serialize(songService.getSongInfoDtoById(id));
    }

    public void addContainedInId(long id, long albumId){
        songService.addContainedInId(id, albumId);
        logger.info("Песне c индексом {} успешно установлена связь с альбомом с индексом {}", id, albumId);
    }

    public void removeContainedInId(long id, long albumId) {
        songService.removeContainedInId(id, albumId);
        logger.info("У песни c индексом {} успешно убрана связь с альбомом с индексом {}", id, albumId);
    }
}
