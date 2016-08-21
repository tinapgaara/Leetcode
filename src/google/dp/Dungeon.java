package google.dp;

/**
 * Created by yingtan on 12/19/15.
 */
public class Dungeon {

    public int calculateMinimumHP(int[][] dungeon) {

        if ((dungeon == null) || (dungeon.length == 0))
            return 0;
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];

        if (dungeon[row-1][col-1] < 0) {
            dp[row-1][col-1] = 1 - (dungeon[row-1][col-1]);
        }
        else {
            dp[row-1][col-1] = 1;
        }

        for (int i = row-2; i >= 0; i --) {
            dp[i][col-1] = dp[i+1][col-1] - dungeon[i][col-1];
            if (dp[i][col-1] < 1) {
                dp[i][col-1] = 1;
            }
        }
        for (int i = col-2; i >= 0 ; i --) {
            dp[row-1][i] = dp[row-1][i+1] - dungeon[row-1][i];
            if (dp[row-1][i] < 1) {
                dp[row-1][i] = 1;
            }
        }
        for (int i = row-2; i >= 0 ; i --) {
            for (int j = col-2; j >= 0 ; j --) {
                int down = Math.max(1, dp[i+1][j] - dungeon[i][j]);
                int right = Math.max(1, dp[i][j+1] - dungeon[i][j]);
                dp[i][j] = Math.min(down, right);
            }
        }
        return dp[0][0];
    }
}
