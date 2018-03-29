package facebook.queue;
import java.util.*;
public class SlidingWindowMaxmimum {
    // stack: latest max, will be removed earlyer
    // queue: latest max, will be removed lateer
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        Deque<Integer> doubleLinkedList = new ArrayDeque<Integer>();
        int[] res =  new int[nums.length - k + 1];
        for (int i = 0 ; i < nums.length; i ++) {
            if (i >= k-1) {
                // the removed element id is: i-k
                if (! doubleLinkedList.isEmpty() && doubleLinkedList.peekFirst() == (i-k)) {
                    doubleLinkedList.pollFirst(); // remove max one
                }
            }
            // make sure linkedlist store decreasing element
            // offer element
            while(! doubleLinkedList.isEmpty() && nums[doubleLinkedList.peekLast()] < nums[i]) {
                doubleLinkedList.pollLast();
            }
            doubleLinkedList.addLast(i);
            if (i >= k-1) {
                res[i-k+1] = nums[doubleLinkedList.peekFirst()];
            }

        }
        return res;
    }
}
