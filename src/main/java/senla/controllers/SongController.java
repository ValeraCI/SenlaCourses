package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.annotations.Loggable;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.services.SongService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @Loggable
    @PostMapping
    public void add(@RequestBody SongCreateDto songCreateDto){
        songService.save(songCreateDto);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        songService.deleteById(id);
    }

    @Loggable
    @GetMapping("/{id}")
    public SongInfoDto findById(@PathVariable("id") Long id){
        return songService.findSongInfoDtoById(id);
    }

    @Loggable
    @GetMapping("search/{parameter}")
    public List<SongInfoDto> findByAlbumId(@PathVariable("parameter") String parameter,
                                                         @RequestParam(name = "findBy",
                                                                 defaultValue = "BY_TITLE") String findBy){
        return songService.findByParameter(parameter, findBy);
    }

}
