package ex03;

import java.util.*;

public class ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = checkInt(sc);
        if (n > 0) {
            List<Animal> list = new ArrayList<>();
            inputType(list, n);
            List<Animal> sortedList = list.stream().sorted((Animal an1, Animal an2) -> {
                int res = 0;
                if (an1 instanceof Herbivore && an2 instanceof Omnivore) res = -1;
                if (an1 instanceof Omnivore && an2 instanceof Herbivore) res = 1;
                return res;
            }).toList();
            printResult(sortedList);
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
            List<String> types = List.of("cat", "dog", "hamster", "guinea");
            if (types.stream().anyMatch(el -> el.equals(type))) {
                String name = sc.next();
                int age = checkInt(sc);
                if (age >0) {
                    switch (type) {
                        case "cat" -> list.add(new Cat(name, age));
                        case "dog" -> list.add(new Dog(name, age));
                        case "hamster" -> list.add(new Hamster(name, age));
                        case "guinea"-> list.add(new GuineaPig(name, age));
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

    public static void printResult(List<Animal> sortedList) {
        for (Animal animal: sortedList) {
            System.out.println(animal);
        }
    }
}
