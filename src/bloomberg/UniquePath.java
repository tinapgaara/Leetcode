package bloomberg;

/**
 * Created by max2 on 10/15/15.
 */
/*
* Leetcode: uniquePath
* A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
*
*
* */
public class UniquePath {

    public int uniquePaths(int m, int n) {
        int[][] uniqueNums = new int[m][n];
        for (int i = 0 ; i < m; i ++) {
            uniqueNums[i][0] = 1;
        }
        for (int j = 0 ; j < n ; j ++) {
            uniqueNums[0][j] = 1;
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                uniqueNums[i][j] = uniqueNums[i-1][j] + uniqueNums[i][j-1];
            }
        }
        return uniqueNums[m-1][n-1];
    }
}
