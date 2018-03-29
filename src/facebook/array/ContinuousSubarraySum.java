package facebook.array;

import java.util.*;

/**
 * Created by yingtan on 12/21/17.
 *
 * 523. Continuous Subarray Sum
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        // k == 0, boundary case to handle
        if (k == 0) {
            if (nums.length >= 2) {
                int sum = 0;
                // special case, to handle
                for (int num : nums) {
                    sum = sum + num;
                }
                if (sum == 0) return true;
            }
            return false;
        }
        Map<Integer, Integer> remainToIndex = new HashMap<>();
        int sum = 0;
        remainToIndex.put(0, -1);
        for (int j = 0 ; j < nums.length; j ++) {
            sum = sum + nums[j];
            int remain = sum % k;
            if (remainToIndex.containsKey(remain)) {
                int index = remainToIndex.get(remain);
                if (j - index >= 2) {
                    return true;
                }
            }
            else {
                // only when map does not have this remain, then, put it into hashmap, we make sure the index is the earilest index for this remain to calculate the longer continuous subarray.
                remainToIndex.put(remain, j);
            }
        }
        return false;
    }
}
