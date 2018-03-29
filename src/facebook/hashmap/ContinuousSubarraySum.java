package facebook.hashmap;

import java.util.HashMap;

/**
 * Created by yingtan on 5/29/17.
 *
 * 523. Continuous Subarray Sum Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 10440
 Total Submissions: 47655
 Difficulty: Medium
 Contributors:
 xuehaohu
 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

 Note:
 The length of the array won't exceed 10,000.
 You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class ContinuousSubarraySum {

    // Solution 2: o(n) + hashmap
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        HashMap<Integer, Integer> remainToIndex = new HashMap<Integer, Integer>();
        // Important !!! this means, if the sum from start % k == 0, then we can use (index - (-1)) as the subarray
        remainToIndex.put(0, -1);

        for (int i = 0 ; i < n; i ++) {
            sum = sum + nums[i];
            if (k == 0) continue;
            int remain = sum % k;
            if (remainToIndex.containsKey(remain)) {
                int index = remainToIndex.get(remain);
                // important, is not i - index + 1
                if (i - index >= 2) return true;
            }
            else {
                remainToIndex.put(remain, i);
            }
        }
        if(k == 0 && sum == 0 && n >= 2) return true;
        return false;
    }


    // Solution 1: max o(n^2)
    public boolean checkSubarraySum_2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        // Important !! dp's length is n + 1, dp[0] = 0
        int[] dp =  new int[nums.length + 1];
        int n = nums.length;
        int sum = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            sum = sum + nums[i];
            dp[i+1] = sum;
            for (int j = 0 ; j < i ; j ++) {
                if((k != 0) && ((dp[i+1] - dp[j]) % k == 0)) {

                    if (i - j + 1 >= 2)  return true;
                }
            }
        }
        if(k == 0 && sum == 0 && n >= 2) return true;
        return false;
    }
}
