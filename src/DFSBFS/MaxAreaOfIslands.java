package DFSBFS;

/**
 * Created by yingtan on 2/20/18.
 */
public class MaxAreaOfIslands {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int[] max = new int[1];
        int globalmax = 0;
        for (int i = 0 ; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 2;
                    max = new int[1];
                    max[0] = 1;
                    dfs(grid, i, j, max);
                    globalmax = Math.max(globalmax, max[0]);
                }
            }
        }
        return globalmax;
    }
    public void dfs(int[][] grid, int i, int j, int[] max) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length) {
                if (grid[newi][newj] == 1) {
                    grid[newi][newj] = 2; // mark as vis
                    max[0] ++;
                    dfs(grid, newi, newj, max);
                }
            }
        }
    }
}
