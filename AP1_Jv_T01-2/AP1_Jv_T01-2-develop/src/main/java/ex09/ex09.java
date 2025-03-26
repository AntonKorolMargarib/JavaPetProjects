package ex09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = checkInt();
        if (n <= 0) {
            System.out.println("Input error. Size <= 0");
            return;
        }

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stringList.add(sc.nextLine());
        }

        String substr = sc.next();
        List<String> resultString = new ArrayList<>();
        findSubstring(stringList, resultString, substr);
        System.out.println(resultString);
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

    public static void findSubstring(List<String> stringList, List<String> resultString, String substr) {
        for (String str: stringList) {
            if (str.contains(substr)) resultString.add(str);
        }
    }

}


