package ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        int n = checkInt();
        if (n > 0) {
            List<Animal> list = new ArrayList<>();
            inputType(list, n);
            printResult(list);
        }

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

    public static void inputType(List<Animal> list, int n) {
        Scanner sc = new Scanner(System.in);
        while (n > 0) {
            String type = sc.next();
            if (type.equals("cat") || type.equals("dog")) {
                String name = sc.next();
                int age = checkInt();
                if (age > 0) {
                    if (type.equals("cat")) {
                        list.add(new Cat(name, age));
                    } else {
                        list.add(new Dog(name, age));
                    }
                } else {
                    System.out.println("Incorrect input. Age <= 0");
                }

            } else {
                System.out.println("Incorrect input. Unsupported pet type");
            }
            n--;
        }
    }

    public static void printResult(List<Animal> list) {
        for (Animal animal: list) {
            System.out.println(animal);
        }
    }
}
