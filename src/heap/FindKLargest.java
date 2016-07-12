package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 10/6/15.
 */
public class FindKLargest {

    public int findKthLargest(int[] nums, int k) {

        NumComparator comparator = new NumComparator();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(comparator);

        for (int i = 0 ; i < nums.length ; i++) {
            queue.offer(nums[i]);
        }

        for (int i = 0; i < k-1 ; i ++) {
            queue.poll();
        }
        return queue.poll();
    }

    public class NumComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (i1.intValue() < i2.intValue()) return 1;
            else if (i1.intValue() > i2.intValue()) return -1;
            else return 0;
        }
    }

    public static void  main(String[] args) {
        FindKLargest ob = new FindKLargest();
        int[] nums = new int[]{2,1,4,6,7};
        int i = ob.findKthLargest(nums, 3);
    }
}
