package ex10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ex10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<User> userList = new ArrayList<>();
        int n = checkInt();
        String userName;
        int userAge;
        while (n > 0) {
            userName = sc.nextLine();
            userAge = checkInt();
            if (userAge > 0) {
                userList.add(new User(userName, userAge));
                n--;
            } else {
                System.out.println("Incorrect input. Age <= 0");
            }
        }
        userList.stream().filter(user -> user.age >= 18).map(user -> user.name)
                .collect(Collectors.joining(", ")).lines().forEach(System.out::println);
        sc.close();
    }

    public static int checkInt() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
            }
        }
    }

}
