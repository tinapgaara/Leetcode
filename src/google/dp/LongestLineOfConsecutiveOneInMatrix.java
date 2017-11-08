package google.dp;

/**
 * Created by yingtan on 10/28/17.
 *
 * 562. Longest Line of Consecutive One in Matrix
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

 Example:
 Input:
 [[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
 Output: 3
 */
public class LongestLineOfConsecutiveOneInMatrix {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int row = M.length;
        int col = M[0].length;
        int horizontalIndex = 0;
        int verticalIndex = 1;
        int diagIndex = 2;
        int antiDiagIndex = 3;
        int max = 0;
        int[][][] dp = new int[row][col][4];
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (M[i][j] == 1) {
                    // initialize
                    for (int k = 0 ; k < 4; k ++) {
                        dp[i][j][k] = 1;
                    }
                    // cal this one based on left one(horiontal)
                    if (j > 0) {
                        dp[i][j][horizontalIndex] = dp[i][j-1][horizontalIndex] + 1;
                    }
                    // cal based on up one(vertial)
                    if (i > 0) {
                        dp[i][j][verticalIndex] = dp[i-1][j][verticalIndex] + 1;
                    }
                    // cal based on diag one
                    if (i > 0 && j > 0) {
                        dp[i][j][diagIndex] = dp[i-1][j-1][diagIndex] + 1;
                    }
                    // cal based on anti-diag one
                    if (i > 0 && j < col - 1) {
                        dp[i][j][antiDiagIndex] = dp[i-1][j+1][antiDiagIndex] + 1;
                    }
                    max = Math.max(max, dp[i][j][horizontalIndex]);
                    max = Math.max(max, dp[i][j][verticalIndex]);
                    max = Math.max(max, dp[i][j][diagIndex]);
                    max = Math.max(max, dp[i][j][antiDiagIndex]);
                }
            }
        }
        return max;
    }
}
