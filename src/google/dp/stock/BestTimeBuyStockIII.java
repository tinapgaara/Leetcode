package google.dp.stock;

/**
 * Created by yingtan on 12/22/15.
 */
public class BestTimeBuyStockIII {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        if (prices.length == 1) return 0;
        int prev = 0;

        int[] delta = new int[prices.length];
        delta[0] = 0;
        for (int i = 1 ; i < prices.length; i ++) {
            delta[i] = prices[i] - prices[prev];
            prev = i;
        }

        int[] maxProfitBefore = new int[prices.length];
        int[] maxProfitAfter = new int[prices.length];

        int max = 0;
        int sum = 0;
        for (int i = 0 ; i < delta.length; i ++) {
            sum = sum + delta[i];
            if (sum < delta[i]) {
                sum = delta[i];
            }
            if (sum > max) {
                max = sum;
            }
            maxProfitBefore[i] = max;
        }

        maxProfitAfter[delta.length-1] = 0;
        sum = 0;
        max = 0;
        for (int i = delta.length-1; i >= 1 ; i --) {
            sum = sum + delta[i];
            if (sum < delta[i]) {
                sum = delta[i];
            }
            if (sum > max) {
                max = sum;
            }
            maxProfitAfter[i-1] = max;
        }

        max = 0;
        for (int i = 0 ; i < prices.length; i ++ ) {
            max = Math.max(max, (maxProfitBefore[i] + maxProfitAfter[i]));
        }
        return max;
    }
}
