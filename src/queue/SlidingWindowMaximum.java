package queue;

/**
 * Created by yingtan on 1/20/18.
 *
 * 239. Sliding Window Maximum
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].
 */
import java.util.*;
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            int[] res = new int[nums.length];
            return res;
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> decreaseMax = new ArrayDeque<>();
        for (int i = 0 ; i < nums.length; i ++) {
            if (decreaseMax.isEmpty()) {
                decreaseMax.push(i);
            }
            else {
                while(! decreaseMax.isEmpty() && nums[decreaseMax.peekLast()] < nums[i]) {
                    decreaseMax.pollLast();
                }
                decreaseMax.offerLast(i);
            }
            if (i == k - 1) {
                // in this first window
                res[0] = nums[decreaseMax.peekFirst()];
            }
            else if (i >= k) {
                // need to poll element
                int popIndex = i - k;
                if (nums[popIndex] == nums[decreaseMax.peekFirst()]) {
                    // remove the max one, change the max queue
                    decreaseMax.pollFirst();
                }
                int resIndex = i - k + 1;
                res[resIndex] = nums[decreaseMax.peekFirst()];
            }
        }
        return res;
    }
}
