package facebook.dp;

/**
 * Created by yingtan on 3/18/18.
 *
 * 188. Best Time to Buy and Sell Stock IV
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Credits:
 Special thanks to @Freezen for adding this problem and creating all test cases.
 */
public class BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        if (k == 0) return 0;
        if (k > prices.length / 2) {
            return greedyMaxProfits(prices);
        }
        int[][] sell = new int[prices.length][k];
        int[][] buy = new int[prices.length][k];
        // max profits when transact before/at ith element at jth transaction
        // buy[i][j] = max(buy[i-1][j], sell[i-1][j-1] - prices[i])
        // sell[i][j] = max(sell[i-1][j], buy[i-1][j] + prices[i])
        buy[0][0] = -1 * prices[0];
        sell[0][0] = 0;
        for(int i = 1; i < prices.length; i ++) {
            buy[i][0] = Math.max(buy[i-1][0], -1 * prices[i]);
            sell[i][0] = Math.max(sell[i-1][0], buy[i-1][0] + prices[i]);
        }
        for (int j = 0; j < k ; j ++) {
            buy[0][j] = -1 * prices[0];
            sell[0][j] = 0;
        }
        for (int i = 1; i < prices.length; i ++) {
            for (int j = 1; j < k; j ++) {
                buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j-1] - prices[i]);
                sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j] + prices[i]);
            }
        }
        int max = 0;
        for (int i = 0 ; i < k ; i++) {
            max = Math.max(max, sell[prices.length - 1][i]); // max transactions time is k, so need to loop
        }
        return max;
    }
    public int greedyMaxProfits(int[] nums) {
        if (nums.length == 1) return 0;
        int profit = 0;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > nums[i-1]) {
                profit = profit + nums[i] - nums[i-1];
            }
        }
        return profit;
    }

}
