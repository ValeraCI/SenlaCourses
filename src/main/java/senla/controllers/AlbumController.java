package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.dto.AlbumDto;
import senla.services.AlbumService;
import senla.util.Json;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final Json json;

    public void add(String jsonAlbumDto){
        AlbumDto albumDto = json.deserialize(jsonAlbumDto, AlbumDto.class);
        albumService.add(albumDto);
    }

    public void remove(long id){
        albumService.deleteById(id);
    }

    public String getById(long id){
        return json.serialize(albumService.getAlbumDtoById(id));
    }
}
