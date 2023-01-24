package senla;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import senla.controllers.AccountController;
import senla.controllers.AlbumController;
import senla.controllers.SongController;

import java.time.LocalDateTime;

@Configuration
@ComponentScan
@PropertySource("application.properties")
public class Application {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Application has started, time= {}", LocalDateTime.now());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        AccountController accountController = context.getBean(AccountController.class);
        AlbumController albumController = context.getBean(AlbumController.class);
        SongController songController = context.getBean(SongController.class);


        accountController.add("{\"id\":1,\"nickname\":\"MORGENSHTERN\",\"role\":\"USER\"}",
                "{\"accountId\":1,\"email\":\"AlisherMorgen@gmail.com\",\"password\":\"OLALA\"}");

        accountController.add("{\"id\":2,\"nickname\":\"Lida\",\"role\":\"USER\"}",
                "{\"accountId\":2,\"email\":\"Lida@gmail.com\",\"password\":\"qwertyLD\"}");


        albumController.add("{\"id\":1,\"title\":\"LAST ONE\",\"songsNum\":1,\"creatorId\":1}");
        albumController.add("{\"id\":2,\"title\":\"МОЁ ИМЯ ЛИДА\",\"songsNum\":1,\"creatorId\":2}");


        songController.add(
                "{\"id\":1,\"title\":\"UGU\",\"genre\":\"RAP\",\"authorsId\":[1],\"albumCreator\":1,\"albumId\":1}");
        songController.add(
                "{\"id\":2,\"title\":\"Лиза\",\"genre\":\"POP\",\"authorsId\":[2],\"albumCreator\":2,\"albumId\":2}");

        albumController.addSongIdIn(1, 1);
        albumController.addSongIdIn(2, 2);

        System.out.println(accountController.getById(1));
        System.out.println(accountController.getById(2));

        System.out.println(accountController.getLoginDetailsById(1));
        System.out.println(accountController.getLoginDetailsById(2));

        System.out.println(albumController.getById(1));
        System.out.println(albumController.getById(2));

        System.out.println(songController.getById(1));
        System.out.println(songController.getById(2));



        albumController.removeSongIdIn(2, 2);

        albumController.remove(2);
        songController.remove(2);
        accountController.remove(2);

        System.out.println("Изменяем данные(Музыка и альбом выступают неизменяемыми объектами в силу логики приложения):");

        accountController.updateData("{\"id\":1,\"nickname\":\"Котээ\",\"role\":\"OWNER\"}");
        accountController.updatePasswordById(1, "12365");

        System.out.println(accountController.getById(1));
        System.out.println(accountController.getLoginDetailsById(1));

        logger.info("Application has ended, time= {}", LocalDateTime.now());
    }
}
