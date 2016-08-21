package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class PerfectSquare {

    public int numSquares(int n) {
        int[] minNum = new int[n+1];
        int len = (int) Math.sqrt((double) n);

        minNum[0] = 0;

        for (int i = 1; i <= len; i ++) {
            minNum[i * i] = 1;
        }
        for (int i = 1; i <= n; i ++) {
            int min = n;
            for (int j = 1; j * j <= i; j ++) {
                min = Math.min(min, minNum[j * j] + minNum[i - j * j]);
            }
            minNum[i] = min;
        }
        return minNum[n];
    }
}
