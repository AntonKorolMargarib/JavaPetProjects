package ex05;

import java.util.Scanner;

public class ex05 {
    public static void main(String[] args) {
        int n;
        n = checkNumber();
        if (n <= 0) {
            System.out.println("Input error. Size <= 0");
            return;
        }

        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = checkNumber();
        }
        int countNeedNumbers = findNumbers(numbers);
        if (countNeedNumbers == 0) {
            System.out.println("There are no such elements");
        } else {
            int[] findedNumbers = new int[countNeedNumbers];
            getNumbers(numbers, findedNumbers);
            for (int number:
                    findedNumbers) {
                System.out.println(number + " ");
            }
        }

    }

    public static int checkNumber() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
            }
        }
    }

    public static int findNumbers(int[] numbers) {
        int countNeedNumbers = 0;
        for (int number : numbers) {
            int lastDigit = number % 10;
            while (number > 10) {
                number /= 10;
            }
            if (lastDigit == number) countNeedNumbers++;
        }
        return countNeedNumbers;
    }

    public static void getNumbers(int[] numbers, int[] findedNumbers) {
        int i = 0;
        for (int number : numbers) {
            int num = number;
            int lastDigit = num % 10;
            while (num > 10) {
                num /= 10;
            }
            if (lastDigit == num)  {
                findedNumbers[i] = number;
                i++;
            }
        }
    }
}


