package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class MaxSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null) return 0;

        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;

        // dp(i, j) represents the length of the square
        // whose lower-right corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }

        // need to keep track of max, inorder to cal max in one block, make length to be row+1 and col+1, and starting from (1,1)
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        // return the area
        return max * max;
    }
}
