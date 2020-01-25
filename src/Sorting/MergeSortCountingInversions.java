package Sorting;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MergeSortCountingInversions {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        long inversions = 0;
        //Check if in sorted or reverse-sorted order first. Otherwise stack overflow when creating tree
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length-1) return 0;
            else if (arr[i] > arr[i+1]) break;
        }
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length-1) return inversions;
            else if (arr[i] < arr[i+1]) break;
            else inversions += arr.length-(i+1);
        }
        inversions = 0;
        TreeNode tree = new TreeNode(arr[0]);
        for (int i = 1; i < arr.length; i++)
            inversions += tree.insert(arr[i]);
        return inversions;
    }

    //Minimal 2-3 Tree, only need basic insert to count inversions. No self balancing
    private static class TreeNode {
        TreeNode left, middle, right;
        int lm, mr;
        int lmsize, mrsize;
        int size;
        
        public TreeNode(int v) {
            lm = v;
            size = 1;
            lmsize = 1;
            mr = 0;
        }
        
        public long insert(int v) {
            long ret = 0;
            if (v < lm) {
                if (left == null)
                    left = new TreeNode(v);
                else
                    ret += left.insert(v);
                ret += lmsize;
                if (middle != null) ret += middle.size;
                if (mr != 0) ret += mrsize;
                if (right != null) ret += right.size;
            }
            else if (lm == v) {
                lmsize++;
                if (middle != null) ret += middle.size;
                if (mr != 0) ret += mrsize;
                if (right != null) ret += right.size;
            }
            else if (mr == 0) {
                mr = v;
                mrsize = 1;
            }
            else if (v < mr) {
                if (middle == null)
                    middle = new TreeNode(v);
                else
                    ret += middle.insert(v);
                ret += mrsize;
                if (right != null) ret += right.size;
            }
            else if (v == mr) {
                if (right != null) ret += right.size;
                mrsize++;
            }
            else { // v > mr
                if (right == null)
                    right = new TreeNode(v);
                else
                    ret += right.insert(v);
            }
            size++;
            return ret;
        }
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            System.out.println(String.valueOf(result));
        }

        scanner.close();
    }
}
