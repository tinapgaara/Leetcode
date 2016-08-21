package google.dp.stock;

/**
 * Created by yingtan on 12/22/15.
 */
public class BestTimeBuyStockII {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        int prevPrice = prices[0];
        int sum = 0;
        for (int i = 1; i < prices.length; i ++) {
            int curPrice = prices[i];
            if (curPrice - prevPrice > 0) {
                sum = sum + curPrice - prevPrice;
            }
            prevPrice = curPrice;
        }
        return sum;
    }
}
