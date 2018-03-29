package DP;

/**
 * Created by yingtan on 1/15/18.
 */
public class MaximumPathSum {

    public int maxPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        for (int i = 1 ; i < grid.length; i ++) {
            grid[i][0] = grid[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j ++) {
            grid[0][j] = grid[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < grid.length;i ++) {
            for (int j = 1; j < grid[0].length; j ++) {
                grid[i][j] = Math.max(grid[i-1][j] + grid[i][j],
                        grid[i][j-1] + grid[i][j]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    // follow up: what if can begin and end at any point? must move down or right, cell may be negative
    public int maxPathSum_followUp(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        for (int i = 1 ; i < grid.length; i ++) {
            grid[i][0] = Math.max(grid[i][0], grid[i-1][0] + grid[i][0]);
        }
        for (int j = 1; j < grid[0].length; j ++) {
            grid[0][j] = Math.max(grid[0][j], grid[0][j-1] + grid[0][j]);
        }
        for (int i = 1; i < grid.length;i ++) {
            for (int j = 1; j < grid[0].length; j ++) {
                grid[i][j] = Math.max(grid[i][j], Math.max(grid[i-1][j] + grid[i][j],
                        grid[i][j-1] + grid[i][j]));
            }
        }
        //loop all cells and find out max ones
        int max = 0;
        for (int i = 0 ; i < grid.length; i ++) {
            for (int j = 0 ; j < grid[0].length; j ++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        return max;
    }
}
