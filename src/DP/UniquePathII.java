package DP;

/**
 * Created by max2 on 10/15/15.
 */
/*
* Leetcode: Unique Paths II
*
*  Follow up for "Unique Paths":

    Now consider if some obstacles are added to the grids. How many unique paths would there be?

    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    For example,
    There is one obstacle in the middle of a 3x3 grid as illustrated below.

    [
      [0,0,0],
      [0,1,0],
      [0,0,0]
    ]
    The total number of unique paths is 2.
* */
public class UniquePathII {

    // using one dim array
    // dp[j] = dp[j] + dp[j-1]
    public int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        int len = obstacleGrid[0].length;
        int[] dp = new int[len];
        dp[0] = 1; // important !!!!
        for (int i = 0 ; i < obstacleGrid.length; i ++) {
            for (int j = 0 ; j < len; j ++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                }
                else if (j > 0) {
                    dp[j] = dp[j] + dp[j-1];
                }
            }
        }
        return dp[len - 1];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 0)
            obstacleGrid[0][0] = 1;
        else {
            obstacleGrid[0][0] = 0;
        }
        for (int i = 1 ; i < row; i ++) {
            if (obstacleGrid[i][0] != 1)
                obstacleGrid[i][0] = obstacleGrid[i-1][0];
            else {
                // obstacle
                obstacleGrid[i][0] = 0;
            }
        }
        for (int j = 1 ; j < col; j ++) {
            if (obstacleGrid[0][j] != 1)
                obstacleGrid[0][j] = obstacleGrid[0][j-1];
            else {
                // obstacle
                obstacleGrid[0][j] = 0;
            }
        }
        for (int i = 1; i <row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (obstacleGrid[i][j] != 1) {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                    //System.out.println(i + " " + j +" " +obstacleGrid[i][j]);
                }
                else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[row-1][col-1];
    }
}
