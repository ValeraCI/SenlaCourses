package senla.controllers;

import framework.annotations.Autowired;
import framework.annotations.Component;
import senla.services.Service;

@Component
public class MusicController {
    private final Service musicService;

    @Autowired
    public MusicController(Service musicService) {
        this.musicService = musicService;
    }

    public String execute() {
        return musicService.execute();
    }
}
