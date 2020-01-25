package StringManipulation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StringsMakingAnagrams {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        for (int i = 0; i < a.length(); i++){
            char at = a.charAt(i);
            if(b.contains(at+"")) {
                b = b.substring(0, b.indexOf(at)) + b.substring(b.indexOf(at)+1);
                a = a.substring(0, i) + a.substring(i+1);
                i--;
            }
        }
        return a.length() + b.length();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        System.out.println(String.valueOf(res));

        scanner.close();
    }
}
