package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.annotations.Loggable;
import senla.dto.song.SongCreateDto;
import senla.services.SongService;
import senla.util.Json;

@Controller
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final Json json;

    @Loggable
    public void add(String jsonSongCreateDto){
        SongCreateDto songCreateDto = json.deserialize(jsonSongCreateDto, SongCreateDto.class);
        songService.add(songCreateDto);
    }

    @Loggable
    public void deleteById(Long id){
        songService.deleteById(id);
    }

    @Loggable
    public String getById(Long id){
        return json.serialize(songService.findSongInfoDtoById(id));
    }
}
