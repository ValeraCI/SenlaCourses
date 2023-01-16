package senla;

import framework.source.ApplicationContext;
import senla.controllers.MusicController;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.createContext("senla");
        MusicController musicController = applicationContext.getBean(MusicController.class);
        System.out.println(musicController.execute());
    }
}
