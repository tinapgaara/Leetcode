package facebook.hashTable;

import java.util.HashMap;

/**
 * Created by yingtan on 5/13/17.
 *
 * 325. Maximum Size Subarray Sum Equals k
 *
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 Note:
 The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

 Example 2:
 Given nums = [-2, -1, 2, 1], k = 1,
 return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

 Follow Up:
 Can you do it in O(n) time?
 *
 */
public class MaxSubarraySumEquals2K {

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> valueToIndex = new HashMap<Integer, Integer>();
        int sum = 0;
        int max = 0;
        for (int i = 0 ; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) {
                max = i + 1; // important !! since i is always increasing, so can set max here
            }
            else if (valueToIndex.containsKey(sum - k)) {
                int prevIndex = valueToIndex.get(sum - k);
                max = Math.max(max, i - prevIndex);
            }
            if (! valueToIndex.containsKey(sum)) { // important !!! for a same value, need to store the min Index for max Len
                valueToIndex.put(sum, i);
            }
        }
        return max;
    }
}
