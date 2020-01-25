package Sorting;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
public class FraudulentActivityNotification {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        MedianHeap h = new MedianHeap(d);
        int counter = 0;
        for (int i = 0; i < d; i++)
            h.add(expenditure[i]);
        for (int i = d; i < expenditure.length; i++)
        {
            if (expenditure[i] >= (h.getMedian() * 2))
                counter++;
            h.remove(expenditure[i-d]);
            h.add(expenditure[i]);
        }
        return counter;
    }

    private static class MedianHeap {
        PriorityQueue<Integer> greater;
        PriorityQueue<Integer> lesser;

        public MedianHeap(int d){
            greater = new PriorityQueue<Integer>(d/2);
            lesser = new PriorityQueue<Integer>(d/2, Collections.reverseOrder());
        }

        public void add(int a) {
            if (a > getMedian())
                greater.add(a);
            else
                lesser.add(a);
            balance();
        }

        private void balance()
        {
            while (Math.abs(greater.size() - lesser.size()) > 1)
            {
                if (greater.size() > lesser.size())
                    lesser.add(greater.poll());
                else
                    greater.add(lesser.poll());
            }
        }

        public void remove(int a)
        {
            if (a > getMedian())
                greater.remove(a);
            else
                lesser.remove(a);
            balance();
        }

        public double getMedian()
        {
            if (greater.size() + lesser.size() == 0) return -1;
            if (greater.size() > lesser.size())
                return greater.peek();
            else if (greater.size() < lesser.size())
                return lesser.peek();
            else
                return (greater.peek() + lesser.peek())/2.0;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        System.out.println(String.valueOf(result));

        scanner.close();
    }
}
