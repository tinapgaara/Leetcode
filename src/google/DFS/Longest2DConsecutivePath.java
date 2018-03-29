package google.DFS;

import java.util.Arrays;

/**
 * Created by yingtan on 11/8/17.
 * hua xue ban
 */
public class Longest2DConsecutivePath {

    public int longestConsecutivePath(int[][] matrix) {
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0 ; i < matrix.length; i ++){
            for (int j = 0 ; j < matrix[0].length; j ++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0 ; i < matrix.length; i ++){
            for (int j = 0 ; j < matrix[0].length; j ++) {
                max = Math.max(max, dfs(matrix, i, j, dp));
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int max = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int nextx = i + dir[0];
            int nexty = j + dir[1];
            if (nextx >= 0 && nextx < matrix.length && nexty >= 0 && nexty < matrix[0].length) {
                if (matrix[nextx][nexty] == matrix[i][j] + 1) {
                    max = Math.max(max, dfs(matrix, nextx, nexty, dp) + 1);
                }
            }
        }
        if (max > dp[i][j]) {
            dp[i][j] = max;
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] m = {{1,2,3}, {0,0,4}, {1,0,5}};
        Longest2DConsecutivePath ob = new Longest2DConsecutivePath();
        System.out.println(ob.longestConsecutivePath(m));
    }
}
