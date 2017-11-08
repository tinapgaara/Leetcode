package google.BFS;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/5/17.
 */
public class TrapRainWaterII {

    public class Point {
        int x;
        int y;
        int height;

        public Point(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public class PointComparater implements Comparator<Point> {
        public int compare(Point x, Point y) {
            return x.height - y.height;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) return 0;
        int row = heightMap.length;
        int col = heightMap[0].length;
        PointComparater comparator = new PointComparater();
        PriorityQueue<Point> queue = new PriorityQueue<Point>(comparator);
        boolean[][] vis = new boolean[row][col];
        int latestMaxHeight = 0;

        for (int i = 0; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if ((i == 0) || (i == row -1) || (j == 0) || (j == col - 1)) {
                    // edges must can't be used for store water
                    Point p = new Point(i, j , heightMap[i][j]);
                    vis[i][j] = true;
                    queue.offer(p);
                }
            }
        }
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1 , 0}, {1, 0}};
        int res = 0;
        // doing bfs using priorityQueue, starting from lower height point
        while(! queue.isEmpty()) {
            Point p = queue.poll();
            // change sea level
            if (p.height > latestMaxHeight) {
                latestMaxHeight = p.height;
            }
            // bfs now
            for (int i = 0; i < dirs.length; i ++) {
                int newX = p.x + dirs[i][0];
                int newY = p.y + dirs[i][1];
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && (! vis[newX][newY]) ) {
                    int newHeight = heightMap[newX][newY];
                    vis[newX][newY] = true;
                    if (newHeight < latestMaxHeight) {
                        res = res + latestMaxHeight - newHeight;
                    }
                    queue.offer(new Point(newX, newY,newHeight));
                }
            }
        }
        return res;
    }
}
