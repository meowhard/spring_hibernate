package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car car1 = new Car(1, "Model1");
      Car car2 = new Car(2, "Model2");
      Car car3 = new Car(3, "Model2");

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");

      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }



      User findableUser = carService.getUserByCar(new Car(2, "Model2"));
      System.out.println("Искомый юзер 2:");
      System.out.println("Id = "+findableUser.getId());
      System.out.println("First Name = "+findableUser.getFirstName());
      System.out.println("Last Name = "+findableUser.getLastName());
      System.out.println("Email = "+findableUser.getEmail());

      User findableUser2 = carService.getUserByCar(new Car(3, "Model2"));
      System.out.println("Искомый юзер 3:");
      System.out.println("Id = "+findableUser2.getId());
      System.out.println("First Name = "+findableUser2.getFirstName());
      System.out.println("Last Name = "+findableUser2.getLastName());
      System.out.println("Email = "+findableUser2.getEmail());

      context.close();
   }
}
