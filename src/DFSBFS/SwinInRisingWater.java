package DFSBFS;

/**
 * Created by yingtan on 2/20/18.
 * 778. Swim in Rising Water
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

 Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

 You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

 Example 1:

 Input: [[0,2],[1,3]]
 Output: 3
 Explanation:
 At time 0, you are in grid location (0, 0).
 You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

 You cannot reach point (1, 1) until time 3.
 When the depth of water is 3, we can swim anywhere inside the grid.
 Example 2:

 Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 Output: 16
 Explanation:
 0  1  2  3  4
 24 23 22 21  5
 12 13 14 15 16
 11 17 18 19 20
 10  9  8  7  6

 The final route is marked in bold.
 We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 Note:

 2 <= N <= 50.
 grid[i][j] is a permutation of [0, ..., N*N - 1].

 */
import java.util.*;
public class SwinInRisingWater {
    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        // Solution1: bfs + priorityQueue
        //return bfs(grid);

        // Solution 2: dfs + dp + binarysearch faster
        return binarySearch(grid);

    }
    public int bfs(int[][] grid) {
        PointComparator comp = new PointComparator();
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(comp);
        queue.offer(new int[]{0, 0, grid[0][0]});

        boolean[][] vis = new boolean[grid.length][grid[0].length];
        int max = 0;
        while(! queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            int val = point[2];
            vis[x][y] = true;
            max = Math.max(val, max);
            if (x == grid.length - 1 && y == grid[0].length - 1) {
                return max;
            }
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                if (newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length && ! vis[newx][newy]) {
                    queue.offer(new int[]{newx, newy, grid[newx][newy]});
                }
            }
        }
        return max;
    }
    public int binarySearch(int[][] grid) {
        int lowTime = grid[0][0];
        int highTime = lowTime;
        for (int i = 0 ; i < grid.length ; i ++) {
            for (int j = 0 ; j < grid[0].length; j ++) {
                highTime = Math.max(highTime, grid[i][j]);
            }
        }
        while(lowTime < highTime) {
            int mid = lowTime + (highTime - lowTime) / 2;
            if(canReach(grid, mid)) {
                highTime = mid;
            }
            else {
                lowTime = mid + 1;
            }
        }
        return highTime;
    }
    public class PointComparator implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            return p1[2] - p2[2];
        }
    }
    public boolean canReach(int[][] grid, int maxHeight) {
        Boolean[][] dp = new Boolean[grid.length][grid[0].length];
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        dfs(grid, dp, vis, 0, 0, maxHeight);
        if (dp[0][0] != null) {
            return dp[0][0];
        }
        else {
            return false;
        }
    }
    public boolean dfs(int[][] grid, Boolean[][] dp, boolean[][] vis, int i, int j, int maxHeight) {
        // base case
        // reach the end
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            if (grid[i][j] <= maxHeight) {
                dp[i][j] = true;
            }
            else {
                dp[i][j] = false;
            }
            return dp[i][j];
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean canReach = false;
        for (int[] dir : dirs) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length && !vis[newi][newj]) {
                vis[newi][newj] = true;
                if (grid[newi][newj] <= maxHeight) {
                    canReach = canReach | dfs(grid, dp, vis, newi, newj, maxHeight);
                }
                else {
                    dp[newi][newj] = false;
                }
                vis[newi][newj] = false;
            }
        }
        dp[i][j] = canReach;
        return dp[i][j];
    }
}
