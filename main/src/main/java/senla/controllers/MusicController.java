package senla.controllers;

import framework.annotations.Autowire;
import framework.annotations.Component;
import senla.services.Service;

@Component
public class MusicController {
    private final Service musicService;

    @Autowire //TODO написать внедрение через интерфейс
    public MusicController(Service musicService) {
        this.musicService = musicService;
    }

    public String execute() {
        return musicService.execute();
    }
}
