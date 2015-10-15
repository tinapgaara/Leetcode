package DP;

/**
 * Created by yingtan on 10/11/15.
 */
/*
*
* Leetcode: Best Time to Buy and Sell Stock III
*
* 这一题约束最多只能买卖两次股票，并且手上最多也只能持有一支股票
*Say you have an array for which the ith element is the price of a given stock on day i.

    Design an algorithm to find the maximum profit. You may complete at most two transactions.

    Note:
    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
因为不能连续买入两次股票，所以买卖两次肯定分布在前后两个不同的区间。
设p(i) = 区间[0,1,2...i]的最大利润 + 区间[i,i+1,....n-1]的最大利润（式子中两个区间内分别只能有一次买卖，这就是第一道题的问题）

我们可以求区间[0,1,2...i]的最大利润；
同理可以从后往前扫描数组求区间[i,i+1,....n-1]的最大利润
* */
public class BuySellStockIII {

    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        if (prices.length == 1) return 0;
        int prev = 0;
        int[] delta = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i ++) {
            delta[prev] = prices[i] - prices[prev];
            prev = i;
        }
        int sum = 0;
        int maxSum = sum;
        int[] maxProfitsPrevHalf = new int[prices.length];
        maxProfitsPrevHalf[0] = 0;

        for (int i = 1 ; i < prices.length; i ++) {  // PAY ATTENTION!!!!
            if (delta[i-1] > (sum + delta[i-1])) {
                sum = delta[i-1];
            }
            else {
                sum = sum + delta[i-1];
            }
            maxSum = Math.max(maxSum, sum);
            maxProfitsPrevHalf[i] = maxSum;
        }

        sum = 0;
        maxSum = sum;
        int[] maxProfitsTailHalf = new int[prices.length];
        maxProfitsTailHalf[0] = 0;

        for (int i = prices.length-2 ; i >= 0; i --) { // PAY ATTENTION!!!!
            if (delta[i] > (sum + delta[i])) {
                sum = delta[i];
            }
            else {
                sum = sum + delta[i];
            }
            maxSum = Math.max(maxSum, sum);
            maxProfitsTailHalf[i] = maxSum;
        }

        maxSum = 0;
        for (int i=0; i < prices.length; i ++) {
            maxSum = Math.max(maxSum, maxProfitsPrevHalf[i] + maxProfitsTailHalf[i]);
        }
        return maxSum;
    }
}
