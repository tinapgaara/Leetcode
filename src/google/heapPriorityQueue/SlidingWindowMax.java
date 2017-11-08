package google.heapPriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/5/17.
 */
public class SlidingWindowMax {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 时间 O(NlogK) 空间 O(K)
        //维护一个大小为K的最大堆，依此维护一个大小为K的窗口，每次读入一个新数，都把堆中窗口最左边的数扔掉，再把新数加入堆中，这样堆顶就是这个窗口内最大的值。

        if(nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i < nums.length; i ++) {
            if (i >= k) {
                queue.remove(nums[i-k]); // o(n)
            }
            queue.offer(nums[i]);
            if (i >= k - 1) {
                res[i - k + 1] = queue.peek();
            }
        }
        return res;
    }
}
