package DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 9/24/15.
 */
public class WallAndGate {

    // Solution 3: quicker: firstly form a queue storing gates. And then go while in that queue
    public void wallsAndGates_3(int[][] rooms) {
        if (rooms == null) return;
        int row = rooms.length;
        if (row == 0) return;
        int col = rooms[0].length;

        Queue<Point> gateQueues = new LinkedList<Point>();
        boolean[][] visitedFlags = new boolean[row][col];
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (rooms[i][j] == 0) {
                    gateQueues.offer(new Point(i, j, 0)); // no BFS in double loop
                }
            }
        }
        BFS(rooms, gateQueues, visitedFlags);
    }

    public void BFS_3(int[][] rooms, Queue<Point> gateQueues, boolean[][] visitedFlags) {
        while ( ! gateQueues.isEmpty()) {
            Point cur = gateQueues.poll();
            int x = cur.m_x;
            int y = cur.m_y;
            int dist = cur.m_dist;

            if ((x >= 0) && (x < rooms.length) && (y >= 0) && (y < rooms[0].length)
                    && ( ! visitedFlags[x][y])) {
                int val = rooms[x][y];
                if (val != (-1))  {
                    if (val != 0) {
                        if (dist < val)
                            rooms[x][y] = dist;
                    }
                    gateQueues.offer(new Point(x-1, y, dist + 1));
                    gateQueues.offer(new Point(x, y-1, dist + 1));
                    gateQueues.offer(new Point(x+1, y, dist + 1));
                    gateQueues.offer(new Point(x, y+1, dist + 1));
                }
                visitedFlags[x][y] = true;
            }
        }
    }

    // Solution2 a little bit quicker: use BFS once in each independent path

    public void wallsAndGates_2(int[][] rooms) {
        if (rooms == null) return;
        int row = rooms.length;
        if (row == 0) return;
        int col = rooms[0].length;

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                boolean[][] visitedFlags = new boolean[row][col];
                Queue<Point> queue = new LinkedList<Point>();
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    queue.offer(new Point(i, j, 0, null));
                    BFS(rooms, queue, visitedFlags); // problem: BFS in double loop
                }
            }
        }
    }

    public void BFS_2(int[][] rooms, Queue<Point> queue, boolean[][] visitedFlags) {
        int minDist = Integer.MAX_VALUE;
        Point minPrevPoint = null;
        while ( ! queue.isEmpty()) {
            Point cur = queue.poll();
            int x = cur.m_x;
            int y = cur.m_y;
            int dist = cur.m_dist;

            if ((x >= 0) && (x < rooms.length) && (y >= 0) && (y < rooms[0].length)
                    && ( ! visitedFlags[x][y])) {
                int val = rooms[x][y];
                if (val == 0) {
                    if (dist < minDist) {
                        minDist = dist;
                        minPrevPoint = cur;
                    }
                }
                else if (val != (-1))  {
                    queue.offer(new Point(x-1, y, dist + 1, cur));
                    queue.offer(new Point(x, y-1, dist + 1, cur));
                    queue.offer(new Point(x+1, y, dist + 1, cur));
                    queue.offer(new Point(x, y+1, dist + 1, cur));
                }
                visitedFlags[x][y] = true;
            }
        }
        int curdist = 1;
        Point cur = minPrevPoint;
        while ((cur != null) && (curdist <= minDist) ){
            int x = cur.m_x;
            int y = cur.m_y;
            rooms[x][y] = curdist;
            Point prev = cur.m_prev;
            System.out.println(rooms[x][y]+", "+curdist);

            cur = prev;
            curdist ++;
        }
    }

    // Solution1 slow: use BFS on each element, and calculate minDistance for each element
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) return;
        int row = rooms.length;
        if (row == 0) return;
        int col = rooms[0].length;

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                boolean[][] visitedFlags = new boolean[row][col];
                Queue<Point> queue = new LinkedList<Point>();
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    queue.offer(new Point(i, j, 0));
                    rooms[i][j] = BFS(rooms, queue, visitedFlags); // problem: BFS in double loop
                }
            }
        }
    }

    public int BFS(int[][] rooms, Queue<Point> queue, boolean[][] visitedFlags) {
        int minDist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int x = cur.m_x;
            int y = cur.m_y;
            int dist = cur.m_dist;

            if ((x >= 0) && (x < rooms.length) && (y >= 0) && (y < rooms[0].length)
                    && (!visitedFlags[x][y])) {
                int val = rooms[x][y];
                if (val == 0) {
                    minDist = Math.min(dist, minDist);
                } else if (val != (-1)) {
                    queue.offer(new Point(x - 1, y, dist + 1));
                    queue.offer(new Point(x, y - 1, dist + 1));
                    queue.offer(new Point(x + 1, y, dist + 1));
                    queue.offer(new Point(x, y + 1, dist + 1));
                }
                visitedFlags[x][y] = true;
            }
        }
        return minDist;
    }

}
