package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senla.annotations.Loggable;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.models.AccountDetails;
import senla.services.api.SongService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @Loggable
    @PostMapping
    public Long save(@Valid @RequestBody SongCreateDto songCreateDto) {
        return songService.save(songCreateDto);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id,
                           @AuthenticationPrincipal AccountDetails accountDetails) {
        songService.deleteById(id, accountDetails);
    }

    @Loggable
    @GetMapping("/{id}")
    public SongInfoDto findById(@PathVariable("id") Long id) {
        return songService.findSongInfoDtoById(id);
    }

    @Loggable
    @GetMapping(value = "search/{parameter}")
    public List<SongInfoDto> findByParameter(@PathVariable("parameter") String parameter,
                                             @RequestParam(name = "findBy",
                                                     defaultValue = "BY_TITLE") String findBy,
                                             @RequestParam(name = "pageNumber", defaultValue = "1") String pageNumber,
                                             @RequestParam(name = "limit", defaultValue = "10") String limit) {

        return songService.findByParameter(parameter, findBy, pageNumber, limit);
    }

    @Loggable
    @GetMapping("search/album/{albumId}")
    public List<SongInfoDto> findByAlbumId(@PathVariable("albumId") Long albumId) {
        return songService.findSongInfoDtoByAlbumId(albumId);
    }
}
