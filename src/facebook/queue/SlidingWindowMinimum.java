package facebook.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMinimum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        Deque<Integer> doubleLinkedList = new ArrayDeque<Integer>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0 ; i < nums.length; i ++) {
            if (i >= k-1) {
                if (doubleLinkedList.peekFirst() == (i-k)) {
                    doubleLinkedList.pollFirst();
                }
            }
            while(! doubleLinkedList.isEmpty() && nums[doubleLinkedList.peekLast()] > nums[i]) {
                doubleLinkedList.pollLast();
            }
            doubleLinkedList.addLast(i);
            if (i -k + 1 >= 0) {
                res[i-k+1] = nums[doubleLinkedList.peekFirst()];
            }
        }
        return res;
    }
}
