package lyft.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIsLandsDiag {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        return dfsIsland(grid);
        //return bfsIsLand(grid);
    }
    public int dfsIsland(char[][] grid) {
        int count = 0;
        for (int i = 0 ; i < grid.length; i ++) {
            for (int j = 0 ; j < grid[0].length; j ++) {
                if(grid[i][j] == '1') {
                    count ++;
                    grid[i][j] = '2';
                    dfs_diag(grid, i, j);
                }
            }
        }
        return count;
    }
    public int bfsIsLand(char[][] grid) {
        int count = 0;
        for (int i = 0 ; i < grid.length; i ++) {
            for (int j = 0 ; j < grid[0].length; j ++) {
                if(grid[i][j] == '1') {
                    count ++;
                    grid[i][j] = '2';
                    bfs_diag(grid, i, j);
                }
            }
        }
        return count;
    }
    // follow up: 对角线也算同一个island
    public void dfs_diag(char[][] grid, int i, int j) {
        int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1 ,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};
        for (int[] dir : dirs) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length) {
                if (grid[newi][newj] == '1') {
                    grid[newi][newj] = '2';// mark vis
                    dfs(grid, newi, newj);
                }
            }
        }
    }
    public void bfs_diag(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1 ,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};
        queue.offer(new int[]{i, j});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            for (int[] dir : dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                if (newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length) {
                    if (grid[newx][newy] == '1') {
                        grid[newx][newy] = '2';
                        queue.offer(new int[]{newx, newy});
                    }
                }
            }
        }
    }
    public void dfs(char[][] grid, int i, int j) {
        int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1 ,0}};
        for (int[] dir : dirs) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length) {
                if (grid[newi][newj] == '1') {
                    grid[newi][newj] = '2';// mark vis
                    dfs(grid, newi, newj);
                }
            }
        }
    }
    public void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1 ,0}};
        queue.offer(new int[]{i, j});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            for (int[] dir : dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                if (newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length) {
                    if (grid[newx][newy] == '1') {
                        grid[newx][newy] = '2';
                        queue.offer(new int[]{newx, newy});
                    }
                }
            }
        }
    }
}
