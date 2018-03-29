package google.dp;

/**
 * Created by yingtan on 11/11/17.
 */
public class CoinChangeII {

    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        // how many ways to construct the amount
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i < coins.length; i ++) {
            if (coins[i] < dp.length) {
                dp[coins[i]] = 1;
            }
        }
        for (int total = 1; total <= amount; total ++) {
            //dp[total] = Integer.MAX_VALUE;
            for (int j = 0 ; j < coins.length; j ++) {
                if(total >= coins[j]) {
                    dp[total] = dp[total] + dp[total - coins[j]];
                }
            }
            System.out.println(total + "," + dp[total]);
        }
        //if (dp[amount] == Integer.MAX_VALUE) {
        //  return 0;
        //}
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChangeII ob = new CoinChangeII()
;
        ob.change(5, new int[]{1,2,5});
    }
}
