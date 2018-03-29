package facebook.dp;

/**
 * Created by yingtan on 2/13/18.
 */
public class BestTimeBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int sellMaxProfits = 0; // first day can not sell
        int buyMaxProfits = -1 * prices[0] - fee; // first day buy
        for (int i = 1; i < prices.length; i ++) {
            buyMaxProfits = Math.max(buyMaxProfits, sellMaxProfits - prices[i] - fee);
            sellMaxProfits = Math.max(sellMaxProfits, buyMaxProfits + prices[i]);
        }
        return sellMaxProfits;
    }

}
