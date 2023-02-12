package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.annotations.Loggable;
import senla.dto.album.AlbumInfoDto;
import senla.dto.album.CreateAlbumDto;
import senla.services.AlbumService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Loggable
    @GetMapping("/{id}")
    public AlbumInfoDto findById(@PathVariable("id") Long id){
        return albumService.findAlbumInfoDtoById(id);
    }

    @Loggable
    @GetMapping("/search/{id}")
    public List<AlbumInfoDto> findByTitle(@PathVariable String title){
        return albumService.findAlbumInfoDtoByTitle(title);
    }

    @Loggable
    @PostMapping
    public void save(@RequestBody CreateAlbumDto createAlbumDto){
        albumService.save(createAlbumDto);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id){
        albumService.deleteById(id);
    }

    @Loggable
    @PostMapping("/{albumId}/{songId}")
    public void addSongIn(@PathVariable("albumId") Long albumId, @PathVariable("songId") Long songId){
        albumService.addSongIn(albumId, songId);
    }

    @Loggable
    @DeleteMapping("/{albumId}/{songId}")
    public void removeSongIn(@PathVariable("albumId") Long albumId, @PathVariable("songId") Long songId){
        albumService.removeSongIn(albumId, songId);
    }

    @Loggable
    @GetMapping("/savedAlbums/{id}")
    public List<AlbumInfoDto> findSavedAlbumsFromAccountId(@PathVariable("id") Long id){
        return albumService.findSavedAlbumsInfoDtoFromAccountId(id);
    }

    @Loggable
    @GetMapping("/createdAlbums/{id}")
    public List<AlbumInfoDto> findCreatedAlbumsFromAccountId(@PathVariable("id") Long id){
        return albumService.findCreatedAlbumInfoDtoFromAccountId(id);
    }

    @Loggable
    @GetMapping
    public List<AlbumInfoDto> findAll(){
        return albumService.findAllAlbumInfoDto();
    }
}
