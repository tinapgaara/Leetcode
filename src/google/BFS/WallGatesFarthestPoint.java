package google.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* 给一个矩阵，某些点有守卫，求哪个点离所有守卫都最远。要求尽可能优化
* */
public class WallGatesFarthestPoint {

    public class Point {
        int x;
        int y;
        int dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int shortestDistance(int[][] grid) {
        if ((grid == null) || (grid.length == 0)) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int[][] distGrid = new int[row][col];
        int count = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (grid[i][j] == 1) {
                    count ++;
                    boolean[][] visitedFlags = new boolean[row][col];
                    BFS(i, j, distGrid, visitedFlags, grid);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if ((distGrid[i][j] != -1) && (distGrid[i][j] != 0)) {
                    min = Math.min(min, distGrid[i][j]);
                }
            }
        }
        if ((min == Integer.MAX_VALUE) || (min < count)) min = -1;
        return min;
    }

    public void BFS(int i, int j, int[][] distGrid, boolean[][] visitedFlags, int[][] grid) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(i, j, 0));
        while (! queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.dist;

            if ((x >= 0) && (x < distGrid.length) && (y >= 0) && (y < distGrid[0].length)) {
                if (!visitedFlags[x][y]) {
                    visitedFlags[x][y] = true;
                    if (grid[x][y] == 2) distGrid[x][y] = -1;
                    else if ((grid[x][y] == 0) || (dist == 0)) { // is door
                        distGrid[x][y] = distGrid[x][y] + dist;
                        queue.offer(new Point(x-1, y, dist+1));
                        queue.offer(new Point(x, y-1, dist+1));
                        queue.offer(new Point(x+1, y, dist+1));
                        queue.offer(new Point(x, y+1, dist+1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WallGatesFarthestPoint ob = new WallGatesFarthestPoint();
        int[][] grid = new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ob.shortestDistance(grid);
    }
}
