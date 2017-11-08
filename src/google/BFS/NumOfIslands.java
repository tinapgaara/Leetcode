package google.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 10/30/17.
 *
 * 200. Number of Islands
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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
public class NumOfIslands {

    public int numIslands(char[][] grid) {
        if ( (grid == null) || (grid.length == 0) ) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0 ; i < row; i  ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i , j);
                    count ++;
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{x,y});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int px = p[0];
            int py = p[1];
            if (px >= 0 && px < grid.length && py >= 0 && py < grid[0].length) {
                if (grid[px][py] == '1') {
                    grid[px][py] = '2';
                    queue.offer(new int[]{px-1, py});
                    queue.offer(new int[]{px+1, py});
                    queue.offer(new int[]{px, py-1});
                    queue.offer(new int[]{px, py+1});
                }
            }
        }
    }
}
