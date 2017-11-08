package google.BFS;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 8/20/17.
 *
 * 505. The Maze II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1


 */
public class TheMazeII {
    public class Point {
        int x;
        int y;
        int dis;

        public Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;

        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if ((maze == null) || (maze.length == 0) || (start == null) || (start.length == 0) || (destination == null) || (destination.length == 0))
            return -1;

        int row = maze.length;
        int col = maze[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        return bfs(start, destination, maze, dirs);
    }

    public int bfs(int[] start, int[] des, int[][] maze, int[][] dirs) {
        int row = maze.length;
        int col = maze[0].length;

        // find min distance for start point to dest -> when reach dest, the min distance to start point
        // Important !!! init path to record min distance for current point to start point
        int[][] path = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                path[i][j] = Integer.MAX_VALUE;
            }
        }

        Point p = new Point(start[0], start[1], 0);

        PointComparator comparator = new PointComparator();

        //use priorityQueue, pick up shorter distance to start point firstly
        PriorityQueue<Point> queue = new PriorityQueue<Point>(comparator);
        queue.offer(p);

        while (!queue.isEmpty()) {
            p = queue.poll();
            int x = p.x;
            int y = p.y;
            // min distance for (x,y) to start
            int dis = p.dis;
            // when reach to dest, the dis must be the min distance to start
            if (x == des[0] && y == des[1]) return dis;

            for (int[] dir : dirs) {
                int tmpx = x;
                int tmpy = y;
                while (!reachWall(tmpx + dir[0], tmpy + dir[1], maze.length, maze[0].length) && maze[tmpx + dir[0]][tmpy + dir[1]] != 1) {
                    tmpx = tmpx + dir[0];
                    tmpy = tmpy + dir[1];
                }

                // stop
                int distance = dis + Math.abs(x - tmpx) + Math.abs(y - tmpy);
                // update min distance for current point to start
                if (path[tmpx][tmpy] > distance) {
                    path[tmpx][tmpy] = distance;

                    // when offer, the point must have smaller distance to start point
                    queue.offer(new Point(tmpx, tmpy, distance));
                }
            }
        }

        return -1;
    }

    private boolean reachWall(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    public class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return p1.dis - p2.dis;
        }
    }
}
