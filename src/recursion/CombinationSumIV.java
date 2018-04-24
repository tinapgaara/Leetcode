package recursion;

import java.util.Arrays;

/**
 * Created by yingtan on 4/17/18.
 *
 * 377. Combination Sum IV
 DescriptionHintsSubmissionsDiscussSolution
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
        return combinationSum4_dp_downToTop(nums, target);
        //return combinationSum4_dp_topToDown(nums, target);
    }
    public int combinationSum4_dp_downToTop(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int sum = 1; sum <= target; sum ++) {
            for (int i = 0; i < nums.length; i ++) {
                if (sum >=  nums[i]) {
                    dp[sum] = dp[sum] + dp[sum - nums[i]];
                }
            }
        }
        return dp[target];
    }
    public int combinationSum4_dp_topToDown(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        // top to down dp, to memorization
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);// important !!! must be -1 instead of 0, because so many dp[i] can be 0. will Time exceed limits
        dp[0] = 1;
        recurCombine(target, nums, dp);
        return dp[target];
    }
    public int recurCombine(int target, int[] nums, int[] dp) {
        //  base case : important !!!
        if (dp[target] != -1) {
            return dp[target];
        }
        int count = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (target >= nums[i]) {
                count = count + recurCombine(target - nums[i], nums, dp);
            }
        }
        dp[target] = count;
        return dp[target];
    }
}
