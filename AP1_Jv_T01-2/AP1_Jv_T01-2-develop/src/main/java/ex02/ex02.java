package ex02;

import java.util.Scanner;

public class ex02 {
    public static void main(String[] args) {
        int seconds = scannSeconds();
        String time = findTime(seconds);
        printTime(time);
    }

    public static int scannSeconds() {
        Scanner sc = new Scanner(System.in);
        int inputSeconds = -1;
        while(true) {
            try {
                inputSeconds = Integer.parseInt(sc.next());
                if (inputSeconds <= 0) {
                    System.out.println("Incorrect time");
                    continue;
                }
                return inputSeconds;
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
            }
        }
    }

    public static String findTime(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);

    }

    public static void printTime(String time) {
        System.out.println(time);
    }
}

