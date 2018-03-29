package google.dp;

/**
 * Created by yingtan on 11/6/15.
 */
/*
House Robber

*You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
* the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
* and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum
amount of money you can rob tonight without alerting the police.
*
* */
public class HouseRobber {

    public int rob_easy(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length==1)  return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }
    public int rob(int[] nums, int start, int end) {
        if (start == end) return nums[start];

        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(dp[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i ++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[end];
    }

    public int rob(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return 0;
        int[] maxMoney = new int[nums.length];
        maxMoney[0] = nums[0];
        if (nums.length == 1) return maxMoney[0];
        if (nums[0]  < nums[1]) maxMoney[1] = nums[1];
        else maxMoney[1] = nums[0];

        for (int i = 2; i < nums.length; i ++) {
            maxMoney[i] = Math.max(maxMoney[i-2] + nums[i], maxMoney[i-1]);
        }
        return maxMoney[nums.length-1];
    }

    /*
    * All house are adjancent as a circle
    *现在房子排成了一个圆圈，则如果抢了第一家，就不能抢最后一家，因为首尾相连了，所以第一家和最后一家只能抢其中的一家，
    * 或者都不抢，那我们这里变通一下，如果我们把第一家和最后一家分别去掉，各算一遍能抢的最大值，然后比较两个值取其中较大
    * 的一个即为所求。那我们只需参考之前的House Robber 打家劫舍中的解题方法，然后调用两边取较大值
    * */

    public int robII(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return 0;
        int[] maxMoney = new int[nums.length];

        if (nums.length == 1) return nums[0];

        // dp[i] = max(dp[i-2] + num[i], dp[i-1])

        // delete head
        maxMoney[0] = 0;
        maxMoney[1] = nums[1];
        for (int i = 2; i < nums.length; i ++) {
            maxMoney[i] = Math.max(maxMoney[i-2] + nums[i], maxMoney[i-1]);
        }

        int max1 = maxMoney[nums.length-1];

        // delete tail
        maxMoney = new int[nums.length];
        maxMoney[0] = 0;
        maxMoney[1] = nums[0];
        for (int i = 2; i < nums.length; i ++) {
            maxMoney[i] = Math.max(maxMoney[i-2] + nums[i-1], maxMoney[i-1]);
        }

        int max2 = maxMoney[nums.length-1];

        return Math.max(max1, max2);
    }
}
