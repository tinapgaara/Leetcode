package bloomberg.stock;

/**
 * Created by yingtan on 11/13/15.
 */
/*
*Say you have an array for which the ith element is the price of a given
* stock on day i.

Design an algorithm to find the maximum profit. You may complete at most
two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must
sell the stock before you buy again).


4 6 1 4 2 :   2 -5 3 -2

2 -3 3 -1

* */
public class BestTimeBuySellStockAtMostTwice {

    public int maxProfit(int[] prices) {

        if ((prices == null) || (prices.length == 0)) return 0;
        int[] delta = new int[prices.length -1];

        for (int i = 1 ; i < prices.length ; i ++) {
            delta[i-1] = prices[i] - prices[i-1];
        }

        int[] maxProfitsBefore = new int[prices.length]; // pay attention here, must be prices.length : not delta's length
        maxProfitsBefore[0] = 0; // pay attention, must be 0 here : at head of array

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < delta.length ; i ++) {

            if (sum + delta[i] < delta[i]) {
                sum = delta[i];
            }
            else {
                sum = sum + delta[i];
            }
            maxSum = Math.max(maxSum, sum);
            maxProfitsBefore[i+1] = maxSum;
        }


        int[] maxProfitsAfter = new int[prices.length]; // pay attention here, must be prices.length : not delta's length
        maxProfitsAfter[prices.length-1] = 0; // pay attention, must be 0 here : at end of array
        sum = 0 ; // must reset here
        maxSum = Integer.MIN_VALUE; // must reset here

        for (int i = delta.length -1; i >= 0 ; i --) {

            if (sum + delta[i] < delta[i]) {
                sum = delta[i];
            }
            else {
                sum = sum + delta[i];
            }
            maxSum = Math.max(maxSum, sum);
            maxProfitsAfter[i] = maxSum;
        }

        maxSum = 0; // reset value must be 0 here.
        for (int i = 0 ; i < prices.length ; i ++) {
            maxSum = Math.max(maxSum, maxProfitsBefore[i] + maxProfitsAfter[i]);
        }
        return maxSum;

    }
}
