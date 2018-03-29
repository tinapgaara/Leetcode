package DP;

/**
 * Created by yingtan on 1/13/18.
 *
 * 377. Combination Sum IV
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.
 */
public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        // top to down dp, to memorization
        int[] dp = new int[target + 1];
        dp[0] = 1;
        /* Sol 1: using down-top to memorization, sometimes, top-down is better than down-top, when solution is found earlier.
        Arrays.fill(dp, -1);
        return recurCombine(nums, target, dp);
        */
        // Sol 2: using top-to-down: Important !!! for the following rules
        // firstly loop target, then loop nums: used when we take [1,3] and [3,1] as different combinations
        // firstly loop nums, then loop target: used when we take [1,3] and [3,1] as same combination:  both use one 1 and one 3
        for (int i = 1; i <= target; i ++) {
            for (int j = 0; j < nums.length; j ++) {
                if (i >= nums[j]) {
                    dp[i] = dp[i] + dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
    public int recurCombine(int[] nums, int target, int[] dp) {
        // base case
        if (target == 0) {
            return 1;
        }
        else if (target < 0) {
            return 0;
        }
        if (dp[target] != -1) { // top-down dp
            return dp[target];
        }
        int total = 0;
        for (int i = 0; i < nums.length; i ++) {
            total = total + recurCombine(nums, target - nums[i], dp);
        }
        // important !!!! need to set to dp array here
        dp[target] = total;
        return total;
    }

    public static void main(String[] args) {
        int[] nums = {1,2};
        CombinationSumIV ob = new CombinationSumIV();
        System.out.println(ob.combinationSum4(nums, 4));
    }
}
