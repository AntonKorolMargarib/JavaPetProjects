package ex07;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ex07 {
    public static void main(String[] args) {
        double[] arr = checkInput();
        if (arr != null) {
            for(double num: arr) {
                System.out.println(num + " ");
            }
            System.out.println("Saving min and max values in file");
            writeResult(arr);
        }

    }

    public static double[] checkInput() {
        double[] arr = null;
        Scanner sc = new Scanner(System.in);
        ClassLoader classLoader = ex07.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(sc.next());
        if (is == null) {
            System.out.println("Input error. File isn't exist.");
        } else {
            arr = writeDouble(is);
        }
        return arr;
    }

    public static double[] writeDouble(InputStream is) {
        int i = 0;
        double[] res = null;
        Scanner sc = new Scanner(is);
        int n = Integer.parseInt(sc.next());
        if (n > 0) {
            res = new double[n];
            while (sc.hasNext()) {
                try{
                    res[i] = Double.parseDouble(sc.next());
                    i++;
                } catch (Exception ignored) {

                }
            }
            if (i != n) {
                System.out.println("Input error. Insufficient number of elements");
                res = null;
            }
        } else {
            System.out.println("Input error. Size <= 0");
        }

        sc.close();
        return res;
    }

    public static void writeResult(double[] arr) {
        try (PrintWriter printWriter = new PrintWriter("src/result.txt")) {
            printWriter.print(findMin(arr) + " ");
            printWriter.print(findMax(arr));
        } catch (Exception ignored) {
        }
    }
    public static double findMin(double[] arr) {
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double findMax(double[] arr) {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

}


