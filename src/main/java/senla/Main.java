package senla;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.configuration.Application;
import senla.controllers.AccountController;
import senla.dto.album.AlbumInfoDto;
import senla.models.Album;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //TODO переписать все!!! sql-скрипты
        //TODO добавить получение всех песен по жанру
        //TODO добавить получение всех песен по названию

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        AccountController accountController = context.getBean(AccountController.class);

        //accountController.save("{\"nickname\":\"Oleg111\",\"email\":\"Oleg111@gmail.com\",\"password\":\"1111Oleg\"}");

        //System.out.println(accountController.getById(1));
        //System.out.println(accountController.getById(2));
        //System.out.println("\n\n\n\n\n\n" + accountController.getById(680) + "\n\n\n\n\n\n");

        /*System.out.println("\n\n\n\n\n\n" + accountController.getById(1) + "\n\n\n\n\n\n");
        accountController.updateData("{\"id\":1,\"nickname\":\"Valerix1\",\"roleId\":2,\"password\":12369}");
        System.out.println("\n\n\n\n\n\n" + accountController.getById(1) + "\n\n\n\n\n\n");
        accountController.updateData("{\"id\":1,\"nickname\":\"Valerix\",\"roleId\":1,\"password\":1122}");
        System.out.println("\n\n\n\n\n\n" + accountController.getById(1) + "\n\n\n\n\n\n");
        */
        //accountController.deleteById(682);
        //System.out.println("\n\n\n\n\n\n" + accountController.getByEmail("Oleg111@gmail.com") + "\n\n\n\n\n\n");

        /*for(Long i = 1L; i < 10; i++){
            List<Album> list = accountController.getSavedAlbumsById(i);

            for (Album a: list) {
                System.out.println("\n\n\n\n\n\n\n" + a.getTitle()+"\n\n\n\n\n\n\n");
            }
        }*/


        System.out.println("\n\n\n\n\n\n" + accountController.getById(2L) + "\n\n\n\n\n\n");

        Set<AlbumInfoDto> list = accountController.getSavedAlbumsById(2L);
        for (AlbumInfoDto a: list) {
            System.out.println("\n\n\n\n\n\n\n"+ a.getId() + ". " + a.getTitle()+"\n\n\n\n\n\n\n");
        }

        accountController.addSavedAlbum(2L, 2L);

        list = accountController.getSavedAlbumsById(2L);
        for (AlbumInfoDto a: list) {
            System.out.println("\n\n\n\n\n\n\n"+ a.getId() + ". " + a.getTitle()+"\n\n\n\n\n\n\n");
        }

        accountController.removeSavedAlbum(2L, 2L);

        list = accountController.getSavedAlbumsById(2L);
        for (AlbumInfoDto a: list) {
            System.out.println("\n\n\n\n\n\n\n"+ a.getId() + ". " + a.getTitle()+"\n\n\n\n\n\n\n");
        }
    }
}
