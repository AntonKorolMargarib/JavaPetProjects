package ex01;

import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x1 = 0.0;
        double y1 = 0.0;
        double x2 = 0.0;
        double y2 = 0.0;
        double x3 = 0.0;
        double y3 = 0.0;
        while (true) {
            try {
                x1 = Double.parseDouble(sc.next());
                y1 = Double.parseDouble(sc.next());
                x2 = Double.parseDouble(sc.next());
                y2 = Double.parseDouble(sc.next());
                x3 = Double.parseDouble(sc.next());
                y3 = Double.parseDouble(sc.next());
            } catch (NumberFormatException ex) {
                System.out.println("Couldn't parse a number. Please, try again");
                continue;
            }

            if (checkTriangle(x1, y1, x2, y2, x3, y3)) {
                double p = perimeter(x1, y1, x2, y2, x3, y3);
                System.out.println(p);
                System.out.printf("Perimeter: %.3f", p);
                break;
            } else {
                System.out.println("It's not a triangle");
            }
        }
    }

    public static double perimeter(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double b = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        return a + b + c;
    }

    public static boolean checkTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double s = 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
        return s != 0.0;
    }
}
