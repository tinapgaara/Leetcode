package facebook.twoPointer;

/**
 * Created by yingtan on 5/13/17.
 *
 * 209. Minimum Size Subarray Sum Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 78049
 Total Submissions: 261564
 Difficulty: Medium
 Contributor: LeetCode
 Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.

 */
public class MinSizeSubarraySumLargerk {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0 ;
        int end = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while (end < nums.length) {
            sum = sum + nums[end];
            while ( (sum >= s) && (start <= end) ) {
                // record the len
                minLen = Math.min(minLen, end - start + 1);
                sum = sum - nums[start];
                start ++;
            }
            end ++;
        }

        if (minLen == Integer.MAX_VALUE) return 0;
        return minLen;
    }

}
