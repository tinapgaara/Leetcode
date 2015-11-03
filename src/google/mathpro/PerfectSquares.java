package google.mathpro;

/**
 * Created by max2 on 11/2/15.
 */
/*
*
* Perfect Squares
*
* Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
* */
public class PerfectSquares {

    public int numSquares(int n) {

        int[] minNum = new int[n+1];
        for (int i = 1 ; i * i <= n ; i ++) {
            minNum[i * i] = 1;
        }
        for (int i = 2; i <= n; i ++) {
            if (minNum[i] == 1) continue;
            minNum[i] = n;
            for (int j = 1; j * j <= i; j ++) {
                int prevValue = j * j;
                minNum[i] = Math.min(minNum[i], minNum[prevValue] + minNum[i - prevValue]);
            }
        }
        return minNum[n];
    }

    public static void main(String[] args) {
        PerfectSquares ob = new PerfectSquares();
        System.out.println(ob.numSquares(13));
    }
}
