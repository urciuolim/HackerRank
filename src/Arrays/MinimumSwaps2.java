package Arrays;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumSwaps2{

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        int counter = 0;
        int temp;
        for (int i = 0; i < copy.length; i++)
        {
            if (copy[i] == i+1) continue;
            for (int j = i+1; j < copy.length; j++)
            {
                if (copy[j] == i+1)
                {
                    temp = copy[i];
                    copy[i] = copy[j];
                    copy[j] = temp;
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        System.out.println(String.valueOf(res));

        scanner.close();
    }
}
