package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senla.annotations.Loggable;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.services.api.SongService;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Loggable
    @PostMapping
    public Long save(@RequestBody SongCreateDto songCreateDto){
        return songService.save(songCreateDto);
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
    public List<SongInfoDto> findByParameter(@PathVariable("parameter") String parameter,
                                                         @RequestParam(name = "findBy",
                                                                 defaultValue = "BY_TITLE") String findBy){
        return songService.findByParameter(parameter, findBy);
    }

}
