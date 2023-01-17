package senla;

import framework.source.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import senla.controllers.MusicController;

import java.time.LocalDateTime;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application has started, time= {}", LocalDateTime.now());

        ApplicationContext applicationContext = ApplicationContext.createContext("senla");
        MusicController musicController = applicationContext.getBean(MusicController.class);
        System.out.println(musicController.execute());

        logger.info("Application has ended, time= {}", LocalDateTime.now());
    }
}
