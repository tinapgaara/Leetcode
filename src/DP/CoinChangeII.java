package DP;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 1/13/18.
 *
 * 518. Coin Change 2
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 Note: You can assume that

 0 <= amount <= 5000
 1 <= coin <= 5000
 the number of coins is less than 500
 the answer is guaranteed to fit into signed 32-bit integer
 Example 1:

 Input: amount = 5, coins = [1, 2, 5]
 Output: 4
 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1
 Example 2:

 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.
 Example 3:

 Input: amount = 10, coins = [10]
 Output: 1
 */
public class CoinChangeII {

    public int change(int amount, int[] coins) {
        if (coins == null) return 0;
        Map<String, Integer> keyToCount = new HashMap<>();
        //return recur(amount, coins, 0, keyToCount);
        return change_dp_one_space(amount, coins);
    }
    public int change_dp_one_space(int amount, int[] coins) {
        if (coins == null) return 0;
        // dp[i][amount] = dp[i-1][amount] + dp[i][amount - coins[i]]
        // dp[amount] = dp[amount] + dp[amount - coins[i]]
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0 ; i < coins.length; i ++) {
            for (int j = 0; j <= amount; j ++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }
    public int change_dp(int amount, int[] coins) {
        // dp[i][amount] = dp[i][amount - coins[i]] + dp[i-1][amount]
        int[][] dp = new int[coins.length][amount+1];
        for (int i = 0 ; i < coins.length; i ++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j ++) {
                int usedI = 0;
                int noUsedI = 0;
                if (j - coins[i] >= 0) {
                    usedI = dp[i][j - coins[i]];
                }
                if (i > 0) {
                    noUsedI = dp[i-1][j];
                }

                dp[i][j] = usedI + noUsedI;
            }
        }
        if (coins.length == 0) {
            if (amount == 0) return 1;
            else return 0;
        }
        return dp[coins.length-1][amount];
    }
    public int recur(int amount, int[] coins, int index, Map<String, Integer> keyToCount) {
        // base case
        if (amount < 0) return 0;
        else if (amount == 0) {
            keyToCount.put(amount+ "_" + index, 1);
            return 1;
        }
        String key = amount + "_" + index;
        if (keyToCount.containsKey(key)) {
            return keyToCount.get(key);
        }
        int count = 0;
        for (int i = index; i < coins.length; i ++) {
            count = count + recur(amount - coins[i], coins, i, keyToCount);
        }
        keyToCount.put(key, count);
        return count;
    }
    public static void main(String[] args) {
        CoinChangeII ob = new CoinChangeII();
        ob.change(5, new int[]{1,2, 5});
    }
}
