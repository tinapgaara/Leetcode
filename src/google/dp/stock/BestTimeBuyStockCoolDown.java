package google.dp.stock;

/**
 * Created by yingtan on 12/22/15.
 */
public class BestTimeBuyStockCoolDown {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0) || (prices.length == 1)) {
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        sell[0] = 0;
        buy[0] = -1 * prices[0];
        for (int i = 1; i < len; i ++) {
            int delta = prices[i] - prices[i-1];
            if (i-2 >= 0) {
                buy[i] = Math.max(buy[i-1] - delta, sell[i-2] - prices[i]);
            }
            else {
                buy[i] = buy[i-1]-delta;
            }
            sell[i] = Math.max(sell[i-1] + delta, buy[i-1] + prices[i]);
        }
        int max = 0;
        for (int i = 0 ; i < sell.length; i ++) {
            if (sell[i] > max) {
                max = sell[i];
            }
        }
        return max;
    }
}
