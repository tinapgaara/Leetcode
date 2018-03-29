package facebook.array;

/**
 * Created by yingtan on 2/11/18.
 *
 * 325. Maximum Size Subarray Sum Equals k(HashMap)
 Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 */
import java.util.*;
public class MaximumSizeSubarraySumEqualsK {

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null) return 0;
        Map<Integer, Integer> sumToSmallestIndex = new HashMap<>();
        int sum = 0;
        int max = 0;
        // important !!! (0 -> -1)
        sumToSmallestIndex.put(0, -1);
        for (int i = 0; i < nums.length; i ++) {
            sum = sum + nums[i];
            if (sumToSmallestIndex.containsKey(sum - k)) {
                int index = sumToSmallestIndex.get(sum - k);
                // important !!!  i - index
                max = Math.max(max, i - index);
            }
            if (! sumToSmallestIndex.containsKey(sum)) {
                sumToSmallestIndex.put(sum, i);
            }
        }
        return max;
    }

}
