package bloomberg.stock;

/**
 * Created by yingtan on 11/13/15.
 */
/*
* Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell
one share of the stock), design an algorithm to find the maximum profit.
* */
public class BestTimeBuySellStockOnce {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;

        int max = 0;
        int min = Integer.MAX_VALUE;

        int[] minPriceBefore = new int[prices.length];
        for (int i = 0 ; i < prices.length; i ++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            minPriceBefore[i] = min;
        }

        int[] maxPriceAfter = new int[prices.length];
        for (int j = prices.length-1; j >= 0 ; j --) {
            if (prices[j] > max) {
                max = prices[j];
            }
            maxPriceAfter[j] = max;
        }

        max = 0;
        for (int i = 0 ; i < prices.length; i ++) {
            max = Math.max(max, maxPriceAfter[i] - minPriceBefore[i]);
        }
        return max;
    }

}
