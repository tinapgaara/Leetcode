package DP;

/**
 * Created by yingtan on 1/15/18.
 *
 * takes an input n and k, you can advance 1-k steps at a time and reach the destination
 */
public class CountNumberOfMovesToClimbStairs {
    public int count(int n, int k) {
        int[] dp = new int[n + 1];
        recurCount(n, k, dp);
        return dp[n];
    }
    public int recurCount(int n, int k, int[] dp) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int total = 0;
        for (int i = 1; i <= k ; i ++) {
            total = total + recurCount(n - i, k, dp);
        }
        dp[n] = total;
        return total;
    }

    public static void main(String[] args) {
        CountNumberOfMovesToClimbStairs ob = new CountNumberOfMovesToClimbStairs();
        System.out.println(ob.count(4, 2));
    }
}
