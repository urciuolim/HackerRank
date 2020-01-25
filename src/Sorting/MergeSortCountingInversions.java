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
        long inversions = mergeSortInv(arr, 0, arr.length-1);
        return inversions;
    }

    static long mergeSortInv(int[] q, int start, int end)
    {
        if ((end-start) == 0) return 0;
        int middle = (end+start)/2;
        long left = mergeSortInv(q, start, middle);
        long right = mergeSortInv(q, middle+1, end);
        long inversions = left + right;
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
                    q[i+1] = q[i];
                q[l] = temp;
                l++; middle++; r++;
            }
        }
        return inversions;
    }
    
    static long bubbleSort(int[] l) {
    	long ret = 0;
    	int[] list = Arrays.copyOf(l, l.length);
    	for (int i = 0; i < list.length; i++) {
    		for (int j = 0; j < list.length-1; j++) {
    			if (list[j] > list[j+1]) {
    				int temp = list[j];
    				list[j] = list[j+1];
    				list[j+1] = temp;
    				ret++;
    			}
    		}
    	}
    	return ret;
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
    		mr = Integer.MIN_VALUE;
    	}
    	
    	public long insert(int v) {
    		long ret = 0;
    		if (v < lm) {
    			if (left == null)
    				left = new TreeNode(v);
    			else
    				ret += left.insert(v);
    			ret += lmsize;
    			if(middle != null) ret += middle.size;
    			if (mr != Integer.MIN_VALUE) ret += mrsize;
    			if (right != null) ret += right.size;
    		}
    		else if (lm == v) {
    			lmsize++;
    			if(middle != null) ret += middle.size;
    			if (mr != Integer.MIN_VALUE) ret += mrsize;
    			if (right != null) ret += right.size;
    		}
    		else if (mr == Integer.MIN_VALUE) {
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
    	Random rand = new Random();
    	long totalInversions = 0;
    	int[] list = new int[10000];
    	for (int i = 0; i < list.length; i++)
    		list[i] = rand.nextInt(100000)+1;
    	TreeNode tree = new TreeNode(list[0]);
    	for (int i = 1; i < list.length; i++)
    		totalInversions += tree.insert(list[i]);
    	System.out.println(totalInversions + " total inversions");
    	System.out.println("Bub says " + bubbleSort(list) + " total inversions");
    	/*
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

        scanner.close();*/
    }
}
