package facebook.array;

/**
 * Created by yingtan on 12/19/17.
 */
public class BuyAndSellStockII {

    public int maxProfit(int[] prices) {
        // using extra space
        /*
        if (prices == null || prices.length == 0) return 0;
        int[] maxProfitsBefore = new int[prices.length];
        int maxprofits = 0;
        int minPriceBuy = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i ++) {
            // max profits can earn when sell the stock currently
            maxprofits = Math.max(maxprofits, prices[i] - minPriceBuy);
            minPriceBuy = Math.min(minPriceBuy, prices[i]);
            if (i > 0) {
                maxProfitsBefore[i] = Math.max(maxProfitsBefore[i-1], maxprofits);
            }
        }
        int maxPriceSell = 0;
        maxprofits = 0;

        int max = 0;
        for (int i = prices.length - 1; i >= 0 ; i --) {
            // max profits can earn when buy the stock currently
            maxprofits = Math.max(maxprofits, maxPriceSell - prices[i]);
            maxPriceSell = Math.max(maxPriceSell, prices[i]);
            max = Math.max(max, maxProfitsBefore[i] + maxprofits);
        }
        return max;
        */
        // using constant space
        if (prices == null || prices.length == 0) return 0;
        // max profits can ear after buying the first stock
        int buy1 = Integer.MIN_VALUE;
        // max profits can ear after selling the first stock
        int sell1 = 0;
        // max profits can ear after buying the second stock
        int buy2 = Integer.MIN_VALUE;
        // max profits can ear after selling the second stock
        int sell2 = 0;

        for (int price : prices) {
            buy1 = Math.max(buy1, -1 * price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }
}
