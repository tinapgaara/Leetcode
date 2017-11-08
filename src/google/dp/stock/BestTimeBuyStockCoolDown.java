package google.dp.stock;

/**
 * Created by yingtan on 12/22/15.
 *
 * Easiest JAVA solution with explanations
 Here I share my no brainer weapon when it comes to this kind of problems.

 1. Define States

 To represent the decision at index i:

 buy[i]: Max profit till index i. The series of transaction is ending with a buy.
 sell[i]: Max profit till index i. The series of transaction is ending with a sell.
 To clarify:

 Till index i, the buy / sell action must happen and must be the last action. It may not happen at index i. It may happen at i - 1, i - 2, ... 0.
 In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy. In that case, we would rather take a rest at n - 1.
 For special case no transaction at all, classify it as sell[i], so that in the end, we can still return sell[n - 1]. Thanks @alex153 @kennethliaoke @anshu2.
 2. Define Recursion

 buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1, or sell at/before i - 2, then buy at i, We cannot sell at i - 1, then buy at i, because of cooldown.
 sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1, or buy at/before i - 1, then sell at i.
 So we get the following formula:

 buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
 sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
 3. Optimize to O(1) Space

 DP solution only depending on i - 1 and i - 2 can be optimized using O(1) space.

 Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
 Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
 Then arrays turn into Fibonacci like recursion:

 b0 = Math.max(b1, s2 - prices[i]);
 s0 = Math.max(s1, b1 + prices[i]);
 4. Write Code in 5 Minutes

 First we define the initial states at i = 0:

 We can buy. The max profit at i = 0 ending with a buy is -prices[0].
 We cannot sell. The max profit at i = 0 ending with a sell is 0.
 Here is my solution. Hope it helps!

 public int maxProfit(int[] prices) {
 if(prices == null || prices.length <= 1) return 0;

 int b0 = -prices[0], b1 = b0;
 int s0 = 0, s1 = 0, s2 = 0;

 for(int i = 1; i < prices.length; i++) {
 b0 = Math.max(b1, s2 - prices[i]);
 s0 = Math.max(s1, b1 + prices[i]);
 b1 = b0; s2 = s1; s1 = s0;
 }
 return s0;
 }
 */
public class BestTimeBuyStockCoolDown {

    // one-dim dp - > constant dp
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        // max profits for buying stock at ith day
        int[] buys = new int[len];
        // max profits for selling stock at ith day
        int[] sells = new int[len];

        // buys[i] = Math.max(buys[i-1], sells[i-2] - prices[i])  buy at ith day, then can't sell at i-1th day
        // sells[i] = Math.max(sells[i-1], buys[i-1] + prices[i]);

        // then, high-dim array -> low-dim array:
        // we can replace buys[i] with b0, buys[i-1] with b1, sells[i] with s0, sells[i-1] with s1 and record sells[i-2] with s2
        // b0 = Math.max(b1, s2 - prices[i])
        // s0 = Math.max(s1, b1 + prices[i])
        // then, moving one day forward: b1 = b0, s2 = s1, s1 = s0
        int b0 = -1 * prices[0];
        // important !!!!
        int b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;
        for (int i = 1; i < prices.length; i ++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return Math.max(s0, b0);
    }

    // one -dime dp
    public int maxProfit_2(int[] prices) {
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
