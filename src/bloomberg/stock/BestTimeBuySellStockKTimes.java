package bloomberg.stock;

/**
 * Created by max2 on 11/13/15.
 */
/*
*
* Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
* */
public class BestTimeBuySellStockKTimes {

    public int maxProfit(int k, int[] prices) {

        if ((prices == null) || (prices.length == 0)) return 0;
        int len = prices.length;
        // sometimes, k is much larger than len, should not use two-dimension array
        int[][] local = new int[len][k+1]; // pay attention : k+1

        int[][] global = new int[len][k+1];

        // initialize: all 0
        // lcoal[i][j] = local[i-1][j] + diff(p[i] - p[i-1]) : p[i] > p[i-1]
        // local[i][j] = global[i-1][j] : p[i] < p[i-1]

        // so , local[i][j] = max(local[i-1][j] + diff, global[i-1][j])

        // global[i][j] = local[i][j]  // has transaction on ith day
        // global[i][j] = global[i-1][j] // no transaction on ith day

        // so , global[i][j] = max(global[i-1][j], local[i][j]);

        for (int i = 1 ; i < prices.length ; i ++) {
           int diff = prices[i] - prices[i-1];
            for (int j =1; j <= k ; j ++) {
                local[i][j] = Math.max(local[i - 1][j] + diff, global[i - 1][j]);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[len-1][k];
    }

    public int maxProfits2(int k, int[] prices) {
        // TODO:
        return 0;
    }
}
