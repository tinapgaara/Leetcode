package facebook.dfs;

/**
 * Created by yingtan on 2/12/18.
 *
 * 200. Number of Islands
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3
 */
import java.util.*;
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j); // or can choose to use bfs
                    //bfs(grid, i, j);
                    count ++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
            if (grid[i][j] == '1') {
                grid[i][j] = '2' ;// mark as vis
                dfs(grid, i + 1, j);
                dfs(grid, i - 1, j);
                dfs(grid, i, j + 1);
                dfs(grid, i, j - 1);
            }
        }
    }
    public void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                if (grid[x][y] == '1') {
                    grid[x][y] = '2'; // mark as vis
                    queue.offer(new int[]{x+1, y});
                    queue.offer(new int[]{x-1, y});
                    queue.offer(new int[]{x, y+1});
                    queue.offer(new int[]{x, y-1});
                }
            }
        }
    }
}
