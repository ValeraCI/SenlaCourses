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
import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.models.AccountDetails;
import senla.services.api.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @Loggable
    @GetMapping("/{id}")
    public AlbumInfoDto findById(@PathVariable("id") Long id) {
        return albumService.findAlbumInfoDtoById(id);
    }

    @Loggable
    @GetMapping("/search/{title}")
    public List<AlbumInfoDto> findByTitle(@PathVariable String title) {
        return albumService.findAlbumInfoDtoByTitle(title);
    }

    @Loggable
    @PostMapping
    public Long save(@RequestBody AlbumCreateUpdateDataDto createAlbumDto) {
        return albumService.save(createAlbumDto);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id,
                       @AuthenticationPrincipal AccountDetails accountDetails) {

        albumService.deleteById(id, accountDetails);
    }

    @Loggable
    @PostMapping("/{albumId}/songs/{songId}")
    public void addSongIn(@PathVariable("albumId") Long albumId,
                          @PathVariable("songId") Long songId,
                          @AuthenticationPrincipal AccountDetails accountDetails) {

        albumService.addSongIn(albumId, songId, accountDetails);
    }

    @Loggable
    @DeleteMapping("/{albumId}/songs/{songId}")
    public void removeSongIn(@PathVariable("albumId") Long albumId,
                             @PathVariable("songId") Long songId,
                             @AuthenticationPrincipal AccountDetails accountDetails) {

        albumService.removeSongIn(albumId, songId, accountDetails);
    }

    @Loggable
    @GetMapping("/savedAlbums/{id}")
    public List<AlbumInfoDto> findSavedAlbumsFromAccountId(@PathVariable("id") Long id) {
        return albumService.findSavedAlbumsInfoDtoFromAccountId(id);
    }

    @Loggable
    @GetMapping("/createdAlbums/{id}")
    public List<AlbumInfoDto> findCreatedAlbumsFromAccountId(@PathVariable("id") Long id) {
        return albumService.findCreatedAlbumInfoDtoFromAccountId(id);
    }

    @Loggable
    @GetMapping
    public List<AlbumInfoDto> findAll() {
        return albumService.findAllAlbumInfoDto();
    }

    @Loggable
    @GetMapping("/recommendations")
    public List<AlbumInfoDto> findRecommendedFor(@AuthenticationPrincipal AccountDetails accountDetails,
                                                 @RequestParam(name = "limit",
                                                 defaultValue = "10") Integer limit) {
        return albumService.findRecommendedFor(accountDetails, limit);
    }
}
