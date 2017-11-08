package google.BFS;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 7/30/17.
 *
 *
 Add to List
 286. Walls and Gates

 * You are given a m x n 2D grid initialized with these three possible values.

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
public class WallAndGate {

    /*
时间 O(NM) 空间 O(N)

Here's my understanding of why this solution guarantees the shortest distance.
We can understand it by level-order BFS.
First we put all 0s to a queue, let's say these these 0s are in level 1. Then from each 0 of the queue, we will go up, down, left and right, all these positions that are rooms are at level 1, and so forth. So assume we only have Gate A and Gate B, and we have a room C and all the other positions are walls. Assume that distance between AC is 3 and distance between BC is 4. So for Gate A, room C is at its level 3, for Gate B, room C is at its level 4. Since we are doing level-order BFS, so C will always first be accessed by the gate that is closer to it, so it will be A.
   0
   INF
   INF
   INF
0  INF(room here)

first level: [0, 0]
then, for the room here, its is on the second floor, so dist = 2, even when the 0 lower than its are pushed to queue firstly and 0 lefter than its are pushed to queue secondly.
*/
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) return;
        int row = rooms.length;
        if (row == 0) return;
        int col = rooms[0].length;

        Queue<int[]> gateQueues = new LinkedList<int[]>();
        //boolean[][] visitedFlags = new boolean[row][col];
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (rooms[i][j] == 0) {
                    // If put here, every loop intialization a flags, this takes a lot of time !!
                    // boolean[][] visitedFlags = new boolean[row][col];
                    gateQueues.offer(new int[]{i, j}); // no BFS in double loop
                }
            }
        }
        // Very Very important !!!!  BFS can start after loop.
        // Scan all gates at once time.
        BFS(rooms, gateQueues);

    }

    public void BFS(int[][] rooms, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] top = queue.remove();
            int row = top[0], col = top[1];
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
        }
    }
}
