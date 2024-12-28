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

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      carService.add(new Car("Nissan", 12345, user1));
      carService.add(new Car("Lada", 23456, user2));
      carService.add(new Car("Mersedes", 34567, user3));
      carService.add(new Car("BMW", 45678, user4));

      carService.getOwner("Nissan", 12345);
      carService.getOwner("Lada", 23456);
      carService.getOwner("Mersedes", 34567);
      carService.getOwner("BMW", 45678);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      User user11 = carService.getOwner("Nissan", 12345);
      User user22 = carService.getOwner("Lada", 23456);
      User user33 = carService.getOwner("Mersedes", 34567);
      User user44 = carService.getOwner("BMW", 45678);

      System.out.println(user11);
      System.out.println(user22);
      System.out.println(user33);
      System.out.println(user44);

      context.close();
   }
}
