package facebook.dp;

/**
 * Created by yingtan on 5/21/17.
 *
 * 334. Increasing Triplet Subsequence Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 38238
 Total Submissions: 98626
 Difficulty: Medium
 Contributor: LeetCode
 Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

 Formally the function should:
 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 Your algorithm should run in O(n) time complexity and O(1) space complexity.

 Examples:
 Given [1, 2, 3, 4, 5],
 return true.

 Given [5, 4, 3, 2, 1],
 return false.
 */
public class IncreasingSubsequenceTriple {

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        // how many numbers <= curNumber
        int[] dp = new int[nums.length];

        for (int i = 0 ; i < dp.length; i ++) {
            dp[i] = 1;
        }

        for (int i = 1; i < nums.length; i ++) {
            for (int j = 0 ; j < i; j ++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] == 3) return true;
                }
            }
        }
        return false;
    }
}
