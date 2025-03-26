package ex08;

import java.util.Scanner;

public class ex08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int currNumber;
        int index = 0;
        int prevNumber;
        try {
            prevNumber = Integer.parseInt(sc.next());
        } catch (NumberFormatException ex) {
            System.out.println("Input error");
            return;
        }

        while (sc.hasNext()) {
            try {
                currNumber = Integer.parseInt(sc.next());
                index++;
                if (currNumber < prevNumber) {
                    System.out.println("The sequence is not ordered from the ordinal number of the number " + index);
                    break;
                }
                prevNumber = currNumber;
            } catch (NumberFormatException ex) {
                System.out.println("The sequence is ordered in ascending order");
                break;
            }
        }
    }
}



