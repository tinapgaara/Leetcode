package facebook.dp;

/**
 * Created by yingtan on 2/13/18.
 */
public class BestTimeBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int maxProfits = 0;
        int minSellPrices = Integer.MAX_VALUE;
        for (int num : prices) {
            maxProfits = Math.max(maxProfits, num - minSellPrices);
            minSellPrices = Math.min(minSellPrices, num);
        }
        return maxProfits;
    }

}
