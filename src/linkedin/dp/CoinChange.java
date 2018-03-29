package linkedin.dp;

/**
 * Created by yingtan on 11/29/17.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int money = 1; money <= amount; money ++) {
            dp[money] = Integer.MAX_VALUE;
            for (int i = 0 ; i < coins.length; i ++) {
                if (money >= coins[i] && dp[money - coins[i]] != Integer.MAX_VALUE) {
                    dp[money] = Math.min(dp[money], dp[money - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) {
            dp[amount] = -1;
        }
        return dp[amount];
    }
}
