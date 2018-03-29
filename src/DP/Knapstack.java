package DP;

/**
 * Created by yingtan on 1/13/18.
 */
public class Knapstack {

    public int maxValue(int[] weights, int[] values, int maxWeight) {
        int itemNum = weights.length;
        int[][] dp = new int[itemNum][maxWeight + 1];
        // dp[i][j] = max(dp[i-1][j], dp[i-1][j - weighti] + valuei)
        // ->  one dim:
        // dp[j] = dp[j], dp[j - weighti] + valuei
        for (int i = 0 ; i < itemNum; i ++) {
            dp[i][0] = 0;
            for (int j = 1; j <= maxWeight; j ++) {
                if (i-1 >= 0) {
                    if (j >= weights[i]) {
                        int withoutI = dp[i-1][j];
                        int withI = dp[i-1][j - weights[i]] + values[i];
                        dp[i][j] = Math.max(withoutI, withI);
                    }
                    else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
                else if (j >= weights[i]){
                    dp[i][j] = values[i];
                }
            }
        }
        return dp[itemNum - 1][maxWeight];
    }
    // use o(weight) memory
    /*
    * Pattern 1:
    * dp[i][x] = dp[i-1][x] + dp[i-1][y]; // y < x;
    * can decrease space :
    * dp[x] = prevdp[x] + prevdp[y]
    * prevdp = dp;
    *
    * for (each i) {
    *    int[] prevdp = dp;
     *    for (each x) {
     *        dp[x] = prevdp[x] + prevdp[y];
     *    }
    * }
    *
    *
    * Pattern 2:
    * dp[i][x] = dp[i-1][x] + dp[i][y], // y < x;
    * can decrease space to:
    * dp[x] = dp[x] + dp[y]
    *
    * for(each i) {
    *   for (each x) {
    *       dp[x] = dp[x] + dp[y]
    *   }
    * }
    *
    *
    * Difference is because:
    * when dp[i][x] = dp[i-1][x] + dp[i-1][y], when updateing dp[x](i), dp[y] already is dp[i][y] instead of dp[i-1][y]
    * so we need an prevdp to store dp[i-1][y]
    *
    * when dp[i][x] = dp[i-1][x] + dp[i][y], when updating dp[x](i), dp[y] is already dp[i][y], so we can use dp[x] = dp[x] + dp[y] directly
    * */
    public int maxValue_better(int[] weights, int[] values, int maxWeight) {
        int itemNum = weights.length;
        int[] dp = new int[maxWeight + 1];
        dp[0] = 0;
        // dp[i][j] = max(dp[i-1][j], dp[i-1][j - weighti] + valuei)
        // ->  one dim:
        // dp[j] = prevdp[j], prevdp[j - weighti] + valuei
        // prevdp = dp;
        for (int i = 0 ; i < itemNum; i ++) {
            int[] prevdp = dp;
            prevdp[0] = 0;
            for (int j = 1; j <= maxWeight; j ++) {
                if (j >= weights[i]) {
                    int withoutI = prevdp[j];
                    int withI = prevdp[j - weights[i]] + values[i];
                    dp[j] = Math.max(withoutI, withI);
                }
                else {
                    dp[j] = prevdp[j];
                }
            }
        }
        return dp[maxWeight];
    }

    public static void main(String[] args) {
        Knapstack ob = new Knapstack();
        int[] weights = {5,3,4,2};
        int[] values = {60,50,70,30};
        System.out.println(ob.maxValue(weights, values, 5));
        System.out.println(ob.maxValue_better(weights, values, 5));
    }
}
