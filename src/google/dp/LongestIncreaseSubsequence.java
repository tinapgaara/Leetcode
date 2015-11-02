package google.dp;

/**
 * Created by yingtan on 11/2/15.
 */
/*
*
* In the first 16 terms of the binary Van der Corput sequence
0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
a longest increasing subsequence is
0, 2, 6, 9, 11, 15.
* */
public class LongestIncreaseSubsequence {

    // Solution: DP
    // Sol 1: O(N^2)
    public int longestIncrease_DP(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length ; i ++) {
            dp[i] = 1; // initial value is 1.
            for (int j = i - 1; j >= 0 ; j --) {
                if (nums[j] < nums[i]) { // ensure increasing nums
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 1;
        for (int i = 0 ; i < dp.length ; i ++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Sol_2: O(NlogN)



    // Solution 1: count sort
    // just used for all numbers in nums are  >= 0.
    public int longestConsecutive(int[] nums) {
        int max = 0;
        for (int i = 0 ; i < nums.length ; i ++) {
            max = Math.max(max, nums[i]);
        }

        int[] counts = new int[max+1];

        for (int i = 0 ; i < nums.length; i ++) {
            counts[nums[i]] ++;
        }
        for (int i = 1; i < counts.length ; i ++) {
            counts[i] = counts[i] + counts[i-1];
        }

        int[] newnum = new int[nums.length];
        for (int i = nums.length -1; i >= 0 ; i --) {
            int num = nums[i];
            int pos = counts[num] -1;
            newnum[pos] = nums[i];
            counts[num] --;
        }

        int prev = 0;
        int cur = 1;
        int maxLen = 1;
        int curLen = 1;
        while (cur < newnum.length) {
            if (newnum[cur] == newnum[prev] + 1) {
                cur ++;
                prev ++;
                curLen ++;
            }
            else {
                maxLen = Math.max(maxLen, curLen);
                prev = cur;
                cur ++;
                curLen = 1;
            }
        }
        if (curLen > 1) {
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestIncreaseSubsequence ob = new LongestIncreaseSubsequence();
        int[] nums = new int[]{100,4,200,1,3,2};
        ob.longestConsecutive(nums);
    }
}
