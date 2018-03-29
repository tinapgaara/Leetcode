package facebook.bfs;

/**
 * Created by yingtan on 12/21/17.
 *
 * 286. Walls and Gates
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 You are given a m x n 2D grid initialized with these three possible values.

 -1 - A wall or an obstacle.
 0 - A gate.
 INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 For example, given the 2D grid:
 INF  -1  0  INF
 INF INF INF  -1
 INF  -1 INF  -1
 0  -1 INF INF
 After running your function, the 2D grid should be:
 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4
 */
import java.util.*;
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0 ; i < rooms.length; i ++) {
            for (int j = 0 ; j < rooms[0].length; j ++) {
                if (rooms[i][j] == 0) {
                    // gate
                    queue.offer(new int[]{i, j});
                }
            }
        }
        bfs(queue, rooms);
    }
    public void bfs(Queue<int[]> queue, int[][] rooms) {
        while(! queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            int[][] dirs = {{0,1}, {0, -1}, {-1,0}, {1, 0}};
            for (int[] dir : dirs) {
                int nextx = x + dir[0];
                int nexty = y + dir[1];
                if (nextx >= 0 && nextx < rooms.length && nexty >= 0 && nexty < rooms[0].length) {
                    if (rooms[nextx][nexty] == Integer.MAX_VALUE) {
                        // this cell has not been visited, and this is the first time been discovered, must be the min distance
                        rooms[nextx][nexty] = rooms[x][y] + 1;
                        queue.offer(new int[]{nextx, nexty});
                    }
                }
            }
        }
    }

}
