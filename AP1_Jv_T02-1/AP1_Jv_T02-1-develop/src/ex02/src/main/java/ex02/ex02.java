package ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ex02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = checkInt(sc);
        if (n > 0) {
            List<Animal> list = new ArrayList<>();
            inputType(list, n);
            printResult(list);
        }
        sc.close();

    }

    public static int checkInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
            }
        }
    }

    public static double checkDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.next());
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
                int age = checkInt(sc);
                if (age <= 0) {
                    System.out.println("Incorrect input. Age <= 0");
                    n--;
                    continue;
                }
                double weight = checkDouble(sc);
                if (weight <= 0) {
                    System.out.println("Incorrect input. Mass <= 0");
                    n--;
                    continue;
                }
                if (type.equals("cat")) {
                    list.add(new Cat(name, age, weight));
                } else {
                    list.add(new Dog(name, age, weight));
                }
            } else {
                System.out.println("Incorrect input. Unsupported pet type");
            }
            n--;
        }
        sc.close();
    }

    public static void printResult(List<Animal> list) {
        for (Animal animal: list) {
            System.out.println(animal);
        }
    }
}
