package ex06;

import java.util.Scanner;

public class ex06 {
    public static void main(String[] args) {
        int n;
        n = checkInt();
        if (n <= 0) {
            System.out.println("Input error. Size <= 0");
            return;
        }

        double[] numbers = new double[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = checkDouble();
        }
        choiceSort(numbers);
        for (double number:
                numbers) {
            System.out.println(number + " ");
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

    public static double checkDouble() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return Double.parseDouble(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
            }
        }
    }

    public static void choiceSort(double[] array) {
        for (int i = 0; i < array.length; i++) {
            int pos = i;
            double min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    pos = j;
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;
        }
    }

}


