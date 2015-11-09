package google.dp;

/**
 * Created by yingtan on 11/8/15.
 */
public class PackageProblem {

    public static int knapsack(int price[], int weight[], int maxWeight) {

        int len = weight.length;
        int[][] dp = new int[len+1][len+1];
        for (int i = 0 ; i <= maxWeight; i ++) {
            dp[0][i] = 0;
        }

        for (int i = 0 ; i < len ; i ++) {
            dp[i][0] = 0;
        }

        for (int i = 0 ; i < len ; i ++) {
            for (int j = 0 ; j < maxWeight; j ++) {
                if (weight[i] >= maxWeight) {
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - weight[i]] + price[i]);
                }
            }
        }
        return dp[len][maxWeight];
    }

    public static void main(String[] args) throws Exception {
        int val[] = {10, 40, 30, 50};
        int wt[] = {5, 4, 6, 3};
        int W = 10;
        System.out.println(knapsack(val, wt, W));
    }

}
