package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.dto.SongCreateDto;
import senla.services.SongService;
import senla.util.Json;

@Controller
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final Json json;

    public void add(String jsonSongCreateDto){
        SongCreateDto songCreateDto = json.deserialize(jsonSongCreateDto, SongCreateDto.class);
        songService.add(songCreateDto);
    }

    public void remove(long id){
        songService.deleteById(id);
    }

    public String getById(long id){
        return json.serialize(songService.getSongInfoDtoById(id));
    }
}
