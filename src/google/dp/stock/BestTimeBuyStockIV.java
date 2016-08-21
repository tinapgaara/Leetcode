package google.dp.stock;

/**
 * Created by yingtan on 12/22/15.
 */
public class BestTimeBuyStockIV {

    public int maxProfit(int k, int[] prices) {

        if ((prices == null) || (prices.length == 0)) return 0;
        int len = prices.length;

        if (k >= len) {
            return maxProfits2(prices);
        }

        int[][] profitsWithTransationInLastDay = new int[prices.length][k+1];
        int[][] dp = new int[prices.length][k+1];

        for (int j = 1; j <= k ; j ++) {
            dp[0][j] = 0;
            profitsWithTransationInLastDay[0][j] = 0;
        }

        for (int i = 1 ; i < prices.length; i ++) {
            for (int j = 1; j <= k ; j ++) {
                int delta = prices[i] - prices[i-1];

                profitsWithTransationInLastDay[i][j] =
                        Math.max(profitsWithTransationInLastDay[i-1][j] + delta,
                                dp[i-1][j-1] + Math.max(delta,0));

                dp[i][j] = Math.max(dp[i-1][j], profitsWithTransationInLastDay[i][j]);
            }
        }
        // initialize: all 0
        // profitsWithTransationInLastDay[i][j] = profitsWithTransationInLastDay[i-1][j] + p[i] - p[i-1]
        // profitsWithTransationInLastDay[i][j] = dp[i-1][j-1] + Math.max(p[i]- p[i-1], 0)

        // dp[i][j] = profitsWithTransationInLastDay[i][j]  // has transaction on ith day
        // dp[i][j] = dp[i-1][j] // no transaction on ith day

        return dp[len-1][k];

    }

    public int maxProfits2(int[] prices) {
        int[] delta = new int[prices.length - 1];

        for (int i = 1; i < prices.length; i ++) {
            delta[i-1] = prices[i] - prices[i-1];
        }
        int sum = 0;
        for (int i = 0 ; i < delta.length; i ++) {
            if (delta[i] > 0) {
                sum = sum + delta[i];
            }
        }
        return sum;
    }
}
