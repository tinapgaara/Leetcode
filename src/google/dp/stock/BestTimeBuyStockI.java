package google.dp.stock;

/**
 * Created by yingtan on 12/22/15.
 */
public class BestTimeBuyStockI {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;

        int max = 0;
        int min = Integer.MAX_VALUE;

        int[] minBuyPriceBefore = new int[prices.length];
        int[] maxSellPriceAfter = new int[prices.length];

        for (int i = 0 ; i < prices.length; i ++) {
            if (prices[i] < min)
                min = prices[i];
            minBuyPriceBefore[i] = min;
        }

        for (int i = prices.length-1 ; i >= 0; i --) {
            if (prices[i] > max) {
                max = prices[i];
            }
            maxSellPriceAfter[i] = max;
        }

        max = 0;
        for (int i = 0 ; i < prices.length; i ++) {
            max = Math.max(max, (maxSellPriceAfter[i] - minBuyPriceBefore[i]));
        }
        return max;
    }
}
