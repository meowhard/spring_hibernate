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
      Car car3 = new Car(3, "Model3");
      Car car4 = new Car(4, "Model4");


      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);
      car4.setUser(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

//      User user2 = carService.getUserByCar(new Car(2, "Model2"));
//      System.out.println("Искомый юзер:");
//      System.out.println("Id = "+user2.getId());
//      System.out.println("First Name = "+user2.getFirstName());
//      System.out.println("Last Name = "+user2.getLastName());
//      System.out.println("Email = "+user2.getEmail());

      context.close();
   }
}
