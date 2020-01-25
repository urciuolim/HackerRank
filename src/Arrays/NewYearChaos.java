package Arrays;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaos {

    // Complete the minimumBribes function below.
    static int[] memory;
    static void minimumBribes(int[] q) {
        memory = new int[q.length];
        int[] arraycopy = Arrays.copyOf(q, q.length);
        try {
            int inversions = mergeSortInv(arraycopy, 0, q.length-1);
            System.out.println(inversions);
        }
        catch (Error e)
        {
            System.out.println("Too chaotic");
        }
    }

    static int mergeSortInv(int[] q, int start, int end)
    {
        if ((end-start) == 0) return 0;
        int middle = (end+start)/2;
        int left = mergeSortInv(q, start, middle);
        int right = mergeSortInv(q, middle+1, end);
        int inversions = left + right;
        if (q[middle] <= q[middle+1]) return inversions;
        int l = start;
        int r = middle+1;
        int temp;
        while (l <= middle && r <= end)
        {
            if (q[l] <= q[r])
                l++;
            else // q[l] > q[r]
            {
                inversions += r-l;
                temp = q[r];
                for (int i = middle; i >= l; i--)
                {
                    memory[q[i]-1]++;
                    if (memory[q[i]-1] > 2) throw new Error("Too chaotic");
                    q[i+1] = q[i];
                }
                q[l] = temp;
                l++; middle++; r++;
            }
        }
        return inversions;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
