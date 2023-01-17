package senla.controllers;

import framework.annotations.Autowired;
import framework.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import senla.services.Service;

@Component
public class MusicController {
    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
    private final Service musicService;

    @Autowired
    public MusicController(Service musicService) {
        this.musicService = musicService;
    }

    public String execute() {
        logger.debug("Method execute started");
        String text = musicService.execute();
        logger.debug("Method execute return '{}'", text);
        return text;
    }
}
