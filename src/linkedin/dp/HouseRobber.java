package linkedin.dp;

/**
 * Created by yingtan on 11/20/17.
 *
 *
 Add to List
 198. House Robber
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        if (nums.length > 1) sum[1] = Math.max(nums[0], nums[1]);
        for (int i = 2 ; i < nums.length ; i ++) {
            sum[i] = Math.max(sum[i-1], sum[i-2] + nums[i]);
        }
        return sum[nums.length - 1];
    }
}
