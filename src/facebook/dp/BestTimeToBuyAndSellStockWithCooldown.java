package facebook.dp;

/**
 * Created by yingtan on 3/18/18.
 *
 * 309. Best Time to Buy and Sell Stock with Cooldown
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 Example:

 prices = [1, 2, 3, 0, 2]
 maxProfit = 3
 transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // buy_i = Math.max(buy_i, sell_i-2 - prices[i])
        // sell_i = Math.max(sell_i, buy_i + prices[i])
        int buyi = -1 * prices[0];
        int selli = 0;
        int selli2 = 0;
        for (int i = 1 ; i < prices.length; i ++) {
            buyi = Math.max(buyi, selli2 - prices[i]);
            selli2 = selli; // save i-1th as i-2th, since we want to proceed to the next day sell.
            selli = Math.max(selli, buyi + prices[i]);
        }
        return selli;
    }
}
