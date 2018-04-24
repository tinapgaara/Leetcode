package apple.bfs;
import java.util.*;
/*
* gates: 0
* obstacle: -1
* room: INF
*
* find  each room shortest path to nearest gate.
* Find sum of shortest paths from rooms to nearest gate.
*
* */
public class ShortestDistFromRoomsToDoor {
    public int sumOfRoomsToGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return 0;
        int row = rooms.length;
        int col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (rooms[i][j] == 0) {
                    // door
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int sum = 0;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int newi = dir[0] + i;
                int newj = dir[1] + j;
                if (newi >= 0 && newi < row && newj >= 0 && newj < col) {
                    if (rooms[newi][newj] == Integer.MAX_VALUE) {
                        rooms[newi][newj] = rooms[i][j] + 1;
                        sum = sum + rooms[newi][newj];
                        queue.offer(new int[]{newi, newj});
                    }
                }
            }
        }
        return sum;
    }
    // cal shortest dis from doors to gate(x, y). -1 means wall
    public int sumOfRoomsToGate(int[][] rooms, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int row = rooms.length;
        int col = rooms[0].length;
        queue.offer(new int[]{x, y});
        int sum = 0;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int newi = dir[0] + i;
                int newj = dir[1] + j;
                if (newi >= 0 && newi < row && newj >= 0 && newj < col) {
                    if (rooms[newi][newj] == Integer.MAX_VALUE) {// this is unvis room
                        rooms[newi][newj] = rooms[i][j] + 1;
                        sum = sum + rooms[newi][newj];
                        queue.offer(new int[]{newi, newj});
                    }
                }
            }
        }
        return sum;
    }
}
