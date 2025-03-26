package ex04;

import java.util.Scanner;

public class ex04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (true) {
            try {
                n = Integer.parseInt(sc.next());
                if (n <= 0) {
                    System.out.println("Input error. Size <= 0");
                    return;
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
                continue;
            }
        }
        int sumNegative = 0;
        int countNegative = 0;
        for (int i = 0; i < n; i++) {
            int digit = 0;
            while (true) {
                try {
                    digit = Integer.parseInt(sc.next());
                    if (digit < 0) {
                        sumNegative += digit;
                        countNegative++;
                    }
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Couldn't parse a number. Please, try again");
                    continue;
                }
            }
        }

        if (countNegative == 0) System.out.println("There are no negative elements");
        else System.out.println(avgNegative(sumNegative, countNegative));
    }

    public static int avgNegative (int sum, int count) {
        return sum / count;
    }
}

