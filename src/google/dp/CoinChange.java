package google.dp;

/**
 * Created by yingtan on 11/11/17.
 *
 * 322. Coin Change
 Description
 Hints
 Submissions
 Discuss
 Solution
 Discuss  Pick One
 You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 coins = [1, 2, 5], amount = 11
 return 3 (11 = 5 + 5 + 1)

 Example 2:
 coins = [2], amount = 3
 return -1.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int total = 1; total <= amount; total ++) {
            dp[total] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j ++) {
                if ((total >= coins[j]) &&
                        (dp[total - coins[j]] != Integer.MAX_VALUE)) {
                    // can construct a result
                    dp[total] = Math.min(dp[total], dp[total - coins[j]] + 1);
                }
            }
            //System.out.println(total + "," + dp[total]);
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[amount];
    }
}
