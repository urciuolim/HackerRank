package StringManipulation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndTheValidString {

    // Complete the isValid function below.
    static String isValid(String s) {
        if (s.length() == 1) return "YES";
        int[] charFreq = new int[26];
        int offset = 97;
        for (char c : s.toCharArray())
            charFreq[(int)c-offset]++;
        Arrays.sort(charFreq);
        int index = 0;
        int last = charFreq.length-1;
        while (charFreq[index] == 0)
            index++;
        if (index == last) return "YES";
        if (charFreq[index] == charFreq[last]) return "YES";
        if (charFreq[index] == charFreq[last-1] && charFreq[index] == charFreq[last]-1) return "YES";
        if (charFreq[index] == 1 && charFreq[index+1] == charFreq[last]) return "YES";
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String s = scanner.nextLine();

        String result = isValid(s);

        System.out.println(result);;

        scanner.close();
    }
}
