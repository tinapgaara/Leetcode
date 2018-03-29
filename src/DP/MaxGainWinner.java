package DP;

/**
 * Created by yingtan on 1/13/18.
 *
 * 5. Pick up coins for max gain
 Even number of coins. Two players take turns at choosing one coin each.
 They can only choose one coins from two coins at the ends of line. Game ends when all coins have neem picked up. Compute max value of the starting players in the game.
 dp公式：
 dp[i, j] = max (num[i] + min(dp[i+1, j-1], dp[i+2, j)),
 num[j] + min(dp[i +1, j-1], dp[i, j-2]);

 */

public class MaxGainWinner {

    public int PredictMaxGainTheWinner(int[] nums) {
        // return max gain for the starter player
        int[][] dp = new int[nums.length][nums.length];
        return computeMax(nums, 0, nums.length - 1, dp);
    }
    public int computeMax(int[] nums, int low, int high, int[][] dp) {
        if (low > high) {
            return 0;
        }
        if (dp[low][high] != 0) {
            return dp[low][high];
        }
        // the second one will max its gain, so left one is Math.min
        // important here
        // if using recursion:
        // dp[i, j] = max (num[i] + min(recur[i+1, j-1], recur[i+2, j)), // using recur here instead of dp[i+1][j-1]
        //                 num[j] + min(recur[i +1, j-1], recur[i, j-2]);
        int chooseLowGain = nums[low] + Math.min(computeMax(nums, low+1, high -1, dp), computeMax(nums, low+2, high, dp));
        int chooseHighGain = nums[high] + Math.min(computeMax(nums, low + 1, high -1, dp), computeMax(nums, low, high - 2, dp));
        dp[low][high] = Math.max(chooseHighGain, chooseLowGain);
        return dp[low][high];
    }

    public static void main(String[] args) {
        int[] nums = {10,25,5,1,10,5};
        MaxGainWinner ob = new MaxGainWinner();
        System.out.println(ob.PredictMaxGainTheWinner(nums));
    }
}
