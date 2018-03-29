package graph;

/**
 * Created by yingtan on 1/18/18.
 *
 * 505. The Maze II
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 */
import java.lang.reflect.Array;
import java.util.*;
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
    // similar to dijkstra
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) return 0;
        PointComparator comparator = new PointComparator();
        //use priorityQueue, pick up shorter distance to start point firstly
        PriorityQueue<Point> queue = new PriorityQueue<Point>(comparator);
        queue.offer(new Point(start[0], start[1], 0));
        int[][] mindist = new int[maze.length][maze[0].length];
        for (int[] min : mindist) {
            Arrays.fill(min, Integer.MAX_VALUE);
        }
        while(! queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == destination[0] && p.y == destination[1]) {
                return p.dis;
            }
            int x = p.x;
            int y = p.y;
            int[][] dirs = {{-1, 0}, {1,0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                int newdist = p.dis;
                while (! reachsBoundary(maze, newx, newy) && maze[newx][newy] != 1) {
                    newx = newx + dir[0];
                    newy = newy + dir[1];
                    newdist ++;
                }
                newx = newx - dir[0];
                newy = newy - dir[1];
                //newdist --; ?????
                if (newdist < mindist[newx][newy] ) { // not visited
                    mindist[newx][newy] = newdist;
                    queue.offer(new Point(newx, newy, newdist));
                }
            }
        }
        return -1;
    }
    public class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return p1.dis - p2.dis;
        }
    }

    public boolean reachsBoundary(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) return true;
        else return false;
    }

    public static void main(String[] args) {
        int[][] maze = {{1,2},{3,4}};
        int[] start = {1,2};
        int[] dest = {3,4};
        TheMazeII ob = new TheMazeII();
        ob.shortestDistance(maze, start, dest);
    }

}
