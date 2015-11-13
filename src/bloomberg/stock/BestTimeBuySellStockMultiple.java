package bloomberg.stock;

/**
 * Created by yingtan on 11/13/15.
 */
public class BestTimeBuySellStockMultiple {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        int prev = prices[0];

        int sum = 0;

        for (int i = 1 ; i < prices.length; i ++) {
            if ((prices[i] - prev) > 0) {
                sum = sum + prices[i] - prev;
            }
            prev = prices[i];
        }
        return sum;
    }
}
