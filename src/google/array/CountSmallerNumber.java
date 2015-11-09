package google.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 11/8/15.
 */
public class CountSmallerNumber {

    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        PriorComparator comparator = new PriorComparator();
        PriorityQueue<Integer> highQueue = new PriorityQueue<Integer>();
        PriorityQueue<Integer> lowQueue =
                new PriorityQueue<Integer>(comparator);
        ArrayList<Integer> res = new ArrayList<Integer>();

        if ((A == null) || (A.length == 0)) return res;
        res.add(0);
        for (int i = 1;  i < A.length; i ++) {
            if (A[i] > A[i-1]) {
                lowQueue.offer(A[i-1]);
                while ((!highQueue.isEmpty()) && (highQueue.peek() < A[i])) {
                    lowQueue.offer(highQueue.poll());
                }
            }
            else {
                highQueue.offer(A[i-1]);
                while ((!lowQueue.isEmpty()) && (lowQueue.peek() > A[i])) {
                    highQueue.offer(lowQueue.poll());
                }
            }
            res.add(lowQueue.size());
        }
        return res;
    }

    public class PriorComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (i1 > i2) {
                return -1;
            }
            else if (i1 < i2) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        CountSmallerNumber ob = new CountSmallerNumber();
        int[] nums = new int[]{1,2,7,8,5};
        System.out.println(ob.countOfSmallerNumberII(nums));
    }
}


