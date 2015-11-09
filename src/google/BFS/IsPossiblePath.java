package google.BFS;

import DFSBFS.Point;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/8/15.
 */
public class IsPossiblePath {

    // 1: obstacle  0:can go
    public boolean hasPath(char[][] grid, int startx, int starty, int endx, int endy) {
        Queue<Point> queue = new LinkedList<Point>();
        Point start = new Point(startx, starty);
        queue.offer(start);

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visitedFlags = new boolean[row][col];

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int curx = cur.m_x;
            int cury = cur.m_y;
            if ((curx >= 0) && (curx < row) && (cury >= 0) && (cury < col)) {
                if (!visitedFlags[curx][cury]) {
                    if ((curx == endx) && (cury == endy)) return true;
                    visitedFlags[curx][cury] = true;
                    if (grid[curx][cury] == '0') {
                        queue.offer(new Point(curx-1, cury));
                        queue.offer(new Point(curx, cury-1));
                        queue.offer(new Point(curx, cury+1));
                        queue.offer(new Point(curx+1, cury));
                    }
                }
            }
        }
        return false;
    }
}
