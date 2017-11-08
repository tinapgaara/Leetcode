package google.dp;

/**
 * Created by yingtan on 8/5/17.
 *
 * There is a fence with n posts, each post can be painted with one of the k colors.

 You have to paint all the posts such that no more than two adjacent fence posts have the same color.

 Return the total number of ways you can paint the fence.

 Note:
 n and k are non-negative integers.
 */
public class PaintFence {

    public int numWays(int n, int k) {
        if (n == 0) return 0;
        /*
        assume we paint ith cell, two cases:
           2  1  1
        1. ith color == i-1th color, then, the ways to paint 1th->ith cells = the ways to paint 1th -> i-1th where i-1th color != i-2th color
        如果和上一个相同的话那么上一个有多少种和上上次不同的染色方案, 那么当前柱子也有多少种染色方案.

           1  1  2
           2  1  2
           1  2  1
           2  2  1
        2. ith color != i-1th color, then the ways to paint 1th->ith cells = the ways to paint 1th -> i-1th * (k-1)
        如果和上一个不同的话那么染色方案就为(k-1)*(之前总共多少染色方案).
        */

        // how many ways to paint 1th -> ith cell
        int total;
        // how many ways to paint 1th -> ith cell where ith cell's color == i-1th cell's color
        int same;
        // how many ways to paint 1th -> ith cell where ith cell's color != i-1th cell's color
        int diff;

        // total ways to paint 1th cell
        total = k;
        // ways to paint 1th cell 's color == 0th cell, since 0th cell is not exist, this is 0
        same = 0;
        // ways to paint 1th cell 's color != 0th cell, since 0th cell is not exist, this is always different, so = k
        diff = k;
        // starting from the second cell
        for (int i = 2; i <= n ; i ++) {
            // the ways to paint 1th -> ith where i-1th color == ith color
            // 等于 ways to paint 1th -> i-1th where i-1th color != i-2th color
            same = diff;
            // the ways to paint 1th -> ith where i-1th color != ith color
            //  等于 ways to paint 1th -> i-1th  * (k-1)
            diff = total * (k-1);
            total = same + diff;
        }
        return total;
    }

    public int numWays_2(int n, int k) {
        if (n == 0) return 0;
        int[] dp = new int[n];
        int[] prevTwoSameColorsWays = new int[n];

        //dp[i-2]'s color == dp[i-1]'s color
        // prevTwoSameColorsWays[i-1] * (k-1);
        //dp[i-2]'s color != dp[i-1]'s color
        // (dp[i-1] - prevTwoSameColorsWays[i-1]) * k

        // update current prevTwoSameColorsWays[i]: current color == prev color only when
        // dp[i-2]'s color != dp[i-1]'s color
        // prevTwoSameColorsWays[i] = dp[i-1] - prevTwoSameColorsWays[i-1];

        dp[0] = k;
        if (n == 1) return k;
        dp[1] = k * k;
        prevTwoSameColorsWays[1] = k;

        for (int i = 2; i < n; i ++) {
            dp[i] = prevTwoSameColorsWays[i-1] * (k-1) + (dp[i-1] - prevTwoSameColorsWays[i-1]) * k;
            prevTwoSameColorsWays[i] = (dp[i-1] - prevTwoSameColorsWays[i-1]);
        }
        return dp[n-1];
    }
}
