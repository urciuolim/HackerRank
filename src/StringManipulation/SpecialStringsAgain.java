package StringManipulation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SpecialStringsAgain {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
    	long[][] count = new long[n][n];
    	boolean[][] isPalin = new boolean[n][n];
    	for (int i = 0; i < n; i++) {
    		count[i][i] = 1;
    		isPalin[i][i] = true;
    	}
    	for (int j = 0; j < n; j++) {
    		for (int i = 0; i < j; i++) {
    			System.out.println("isPlain[" + i + "+1][" + j + "-1]: " + isPalin[i+1][j-1] + " | " + s.charAt(i) + " " + s.charAt(j));
    			if (isPalin[i+1][j-1] && s.charAt(i) == s.charAt(j))
    				isPalin[i][j] = true;
    		}
    	}
    	System.out.println(Arrays.deepToString(isPalin));
    	return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        //System.out.println(String.valueOf(result));

        scanner.close();
    }
}
