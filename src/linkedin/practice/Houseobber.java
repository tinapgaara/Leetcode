package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 */
public class Houseobber {

    public int rob(int[] nums) {
        // dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2 ; i < nums.length; i ++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[nums.length -1];
    }
}
