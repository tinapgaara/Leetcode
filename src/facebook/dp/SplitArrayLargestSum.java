package facebook.dp;

import java.util.Arrays;

/**
 * Created by yingtan on 2/19/18.
 * 410. Split Array Largest Sum
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:

 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)
 Examples:

 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        //return spliArray_recursion(nums, m); Time Exceed Limit !!!!
        //return spliArray_dp_topToDown(nums, m); dp
        return spliArray_binarySearch(nums, m);
    }
    // Solution 1:  use recursion, not do memoriation, Time Exceed Limits
    public int spliArray_recursion(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;
        int[] max = new int[1];
        max[0] = 0;
        recurSplit(nums, 0, m, max, 0);
        return max[0];
    }
    public void recurSplit(int[] nums, int index, int m, int[] globalmax, int localmax) {
        if (index >= nums.length) {
            if (m == 0) {
                // find a solution
                globalmax[0] = Math.max(globalmax[0], localmax);
            }
            return;
        }
        int sum = 0;
        for (int i = index; i < nums.length; i ++) {
            sum = sum + nums[i];
            // split nums[i+1 - end] into m parts, this can be memorized -> a better solution dp
            // dp[m][j] : min sum to split(j-end) to m parts
            recurSplit(nums, i + 1, m, globalmax, Math.max(localmax, sum));
        }
    }
    // Solution 2 : according to Solution 1, have a better dp version
    public int spliArray_dp_topToDown(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;
        int[][] dp = new int[m + 1][nums.length];
        recurSplit_dp(nums, 0, m, dp);
        return dp[m][0];
    }
    public int recurSplit_dp(int[] nums, int index, int m, int[][] dp) {
        if (index >= nums.length) return Integer.MAX_VALUE;
        // base case
        if (m == 1) {
            int sum = 0;
            for (int i = index; i < nums.length; i ++) {
                sum = sum + nums[i];
            }
            dp[m][index] = sum;
        }
        if (dp[m][index] != 0) {
            return dp[m][index];
        }
        int sum = 0;
        int localmax = 0;
        int min = Integer.MAX_VALUE;
        for (int i = index ; i < nums.length; i ++) {
            sum = sum + nums[i];
            localmax = Math.max(recurSplit_dp(nums, i + 1, m - 1, dp), sum);
            min = Math.min(min, localmax);
        }
        dp[m][index] = min;
        return dp[m][index];
    }
    // Solution 3:  using binary search, since sums[i] is an increasing array, we can search maxNum - sum, and find out minsum which is valid
    // use greedy + binary search
    public int spliArray_binarySearch(int[] nums, int m) {
        long sum = 0;
        int maxnum = 0;
        for (int num : nums) {
            maxnum = Math.max(maxnum, num);
            sum = sum + num;
        }
        long low = maxnum;
        long high = sum;
        while(low < high) {
            long mid = low + (high - low) / 2;
            if (tooSmaller(nums, m, mid)) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return (int)high;
    }
    // very important !!! too difficult !!!!!
    public boolean tooSmaller(int[] nums, int m, long target) {
        // if can split nums into m parts and minsum is target
        int sum = 0;
        int cut = 1;// important !!! init this to be 1
        for (int num : nums) {
            sum = sum + num;
            if (sum > target) {
                // already > target, which means target can not be the minsum, so, cut here
                cut ++;
                sum = num;
                if (cut > m) {
                    return true;
                }
            }
        }
        return false;
    }
}
