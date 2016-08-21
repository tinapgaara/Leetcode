package greedy;

/**
 * Created by yingtan on 10/10/15.
 */
/*
*
* Leetcode:   Best Time to Buy and Sell Stock
*
* Say you have an array for which the ith element is the price of a given stock on day i.

    Design an algorithm to find the maximum profit.
*
* */
public class BestTimeStock {

    /*
    * Best Time to Buy and Sell Stock II
    *
    * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However,
     *you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    *
    * Solution: Greedy
    *
    * eg:
    * [3 4 0 6]
    * calculate delta array:
    * [1 -4 6]
    * twoPointers.sum up all >0 elements:
    * = 1+ 6 = 7
    * */
    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        int prev = prices[0];

        int sum = 0;
        for (int i = 1; i < prices.length; i ++) {
            int delta = (prices[i]) - prev;
            if (delta > 0) {
                sum  = sum + delta;
            }
            prev = prices[i];
        }
        return sum;
    }

}
