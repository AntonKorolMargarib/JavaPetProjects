package ex04;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex04 {
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

    public static void inputType(List<Animal> list, int n) {
        Scanner sc = new Scanner(System.in);
        while (n > 0) {
            String type = sc.next();
            List<String> types = List.of("cat", "dog");
            if (types.stream().anyMatch(el -> el.equals(type))) {
                String name = sc.next();
                int age = checkInt(sc);
                if (age >0) {
                    switch (type) {
                        case "cat" -> list.add(new Cat(name, age));
                        case "dog" -> list.add(new Dog(name, age));
                    }
                } else {
                    System.out.println("Incorrect input. Age <= 0");
                    n--;
                    continue;
                }
            } else {
                System.out.println("Incorrect input. Unsupported pet type");
            }
            n--;
        }
        sc.close();
    }

    public static void printResult(List<Animal> animals) {
        animals.stream().peek(animal -> {
            if (animal.getAge() > 10) {
                Class<?> animalClass = animal.getClass();
                try {
                    Field age = animalClass.getSuperclass().getDeclaredField("age");
                    age.setAccessible(true);
                    int animalAge = (int) age.get(animal);
                    age.set(animal, animalAge + 1);
                } catch (Exception ignored) {}
            }
        }).forEach(System.out::println);
    }
}
