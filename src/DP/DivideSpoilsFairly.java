package DP;

/**
 * Created by yingtan on 1/13/18.
 * Suppose you are given an array of n integers {a1,……, an} between 0 and M.
 * Give an algorithm for dividing these integers into two sets x and y such that the difference of the sum of the integers in each set,
 * is minimized.
 *
 * actually, same as knapsnack.
 *
 * Limitation:  the total weight must be smaller than totalWeight/2
 * To compute:  the max weight
 */
import java.util.*;
public class DivideSpoilsFairly {

    public int minDiff(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total = total + num;
        }
        // dp[i][j] : if theive 1 can have total value j with items[0-i]
        boolean[][] dp = new boolean[nums.length][total / 2 + 1];
        for (int i = 0 ; i < nums.length; i ++) {
            dp[i][0] = true;
            for (int j = 1; j <= total / 2; j ++) {
                if (i-1 >= 0) {
                    if (j >= nums[i]) {
                        // dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]]
                        dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
                    }
                    else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
                else {
                    if (j == nums[i]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        // from max to min
        for (int possiblesum = total / 2; possiblesum >= 0 ; possiblesum --) {
            if (dp[nums.length - 1][possiblesum]) {
                // theive 1 can use items[0-i] to form total value possibleSum, and this possiblesum is largest one
                int theive1Sum = possiblesum;
                int theive2Sum = total - theive1Sum;
                return theive2Sum - theive1Sum;
            }
        }
        return total;
    }
    // decrease space:
    // dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
    // dp[j] = prev[j] || prev[j - nums[i]]
    // prev = dp;
    public int minDiff_simple(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total = total + num;
        }
        boolean[] dp = new boolean[total / 2 + 1];
        dp[0] = true;
        for (int i = 0 ; i < nums.length; i ++) {
            boolean[] prevdp = dp;
            for (int j = 1; j <= total / 2; j ++) {
                if (j >= nums[i]) {
                    dp[j] = prevdp[j] || prevdp[j - nums[i]];
                }
                else {
                    dp[j] = prevdp[j];
                }
            }
        }
        for (int theive1Sum = total / 2; theive1Sum >= 0 ; theive1Sum --) {
            if (dp[theive1Sum]) {
                int theive2Sum = total - theive1Sum;
                return theive2Sum - theive1Sum;
            }
        }
        return total; // one thief takes all
    }

    public static void main(String[] args) {
        DivideSpoilsFairly ob = new DivideSpoilsFairly();
        int[] nums = {65,35,245,195,65,150,275,155,120,320,75,40,200,100,220,99};
        ob.minDiff_simple(nums);
    }
}
