package ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = checkInt(sc);
        if (n > 0) {
            List<Animal> list = new ArrayList<>();
            inputType(list, n, sc);
            AnimalIterator animalIterator = new AnimalIterator(list);
            while (animalIterator.hasNext()) {
                System.out.println(animalIterator.next());
            }
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

    public static void inputType(List<Animal> list, int n, Scanner sc) {
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
    }
}
