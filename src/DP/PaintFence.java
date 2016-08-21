package DP;

/**
 * Created by yingtan on 9/23/15.
 */
public class PaintFence {

    public int numWays(int n, int k) {

        if (n == 0) return 0;
        if (k == 0) return 0;

        int[] ways = new int[n];
        int[] nonConsecutive = new int[n];
        ways[0] = k;
        nonConsecutive[0] = k;
        // nonConsecutive[n] : how many different n-digit numbers whose last two digits are different
        for (int i = 1; i < n ; i ++) {
            ways[i] = nonConsecutive[i-1] * k + (ways[i-1] - nonConsecutive[i-1]) * (k-1);
            // ways[i-1] * (k-1) =
            //      (nonConsecutive[i-1] * (k-1) + (ways[i-1] - nonConsecutive[i-1]) * (k-1))
            nonConsecutive[i] = ways[i-1] * (k-1);
        }

        return ways[n-1];

    }
}
