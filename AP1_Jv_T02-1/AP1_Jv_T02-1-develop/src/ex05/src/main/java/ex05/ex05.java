package ex05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ex05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long startTime = System.currentTimeMillis();
        int n = checkInt(sc);
        if (n > 0) {
            List<Animal> list = new ArrayList<>();
            inputType(list, n, sc);
            goToWalk(list, startTime);
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

    public static void goToWalk(List<Animal> animals, long startTime) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (Animal animal: animals) {
            executor.submit(() -> {
                double currentTime = (System.currentTimeMillis() - startTime) / 1000.0;
                double walkTime = animal.goToWalk();
                System.out.print(animal);
                System.out.printf(", start time = %.2f, end time = %.2f%n", currentTime, currentTime + walkTime);
            });
        }
        executor.shutdown();
    }

}
