package google.dp;

/**
 * Created by yingtan on 11/8/15.
 */
public class PaintFence2Color {
    /*
    *
    * 就一个编程题，n个borad，两个颜色，相邻的三个board不能相同颜色，求一共能有多少可能的涂
    * */

    /*
    * ways[i] = nonConsecutive[i-1] * 2 + (ways[i-1] - nonConsecutive[i-1]) * 1
    *           == noConsecutive[i-1] * k + (ways[i-1] - noConsecutive[i-1]) * (k-1)
    * // nonconsecutive: can have 2 colors to choose
    * // consecutive: just 1 color to choose
    *
    * nonConsecutive[i] = nonConsecutive[i-1] * 1 + (ways[i-1] - nonConsecutive[i-1]) * 1
    *                   = ways[i-1] * (k-1) // k == 2
    * // nonConsecutive: can has one color to choose to make sure not consecutive now
    *  // consecutive: can has one color to choose to make sure not consecutive now
    * */

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

    public int numWays(int[] board) {
        int len = board.length;
        int[] ways = new int[len];
        int[] noConsecutive = new int[len];

        ways[0] = 2;
        noConsecutive[0] = 2;

        for (int i =1; i < len ; i ++) {
            ways[i] = (ways[i-1] - noConsecutive[i-1]) * 1 +
                    noConsecutive[i-1] * 2;
            noConsecutive[i] = noConsecutive[i-1] * 1 +
                    (ways[i-1] - noConsecutive[i-1]) * 1;
        }
        return ways[len-1];
    }


}
