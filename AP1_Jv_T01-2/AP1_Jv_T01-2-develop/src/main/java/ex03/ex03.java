package ex03;

import java.util.Scanner;

public class ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while(true) {
            try {
                n = Integer.parseInt(sc.next());
                if (n <= 0) {
                    System.out.println("Digit has to be >= 0");
                    continue;
                }
                try {
                    int res = fib(n);
                    System.out.println(res);
                    break;
                } catch (StackOverflowError ex) {
                    System.out.println("Too large n");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
            }
        }
    }

    public static int fib(int n) {
        if (n == 1 || n == 2)
            return 1;
        return fib(n-1) + fib(n-2);
    }
}

