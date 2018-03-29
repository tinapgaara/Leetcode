package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yingtan on 1/18/18.
 */
public class TheMazeIII {

    public class Point {
        int x;
        int y;
        int dis;
        String str; // lex string starts from start to cur point

        public Point(int x, int y, int dis, String str) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.str = str;
        }
    }
    // similar to dijkstra
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0) return "";
        PointComparator comparator = new PointComparator();
        //use priorityQueue, pick up shorter distance to start point firstly
        PriorityQueue<Point> queue = new PriorityQueue<Point>(comparator);
        queue.offer(new Point(ball[0], ball[1], 0, ""));
        int[][] mindist = new int[maze.length][maze[0].length];
        String[][] minstr = new String[maze.length][maze[0].length];
        for (int[] min : mindist) {
            Arrays.fill(min, Integer.MAX_VALUE);
        }
        for (String[] min : minstr) {
            Arrays.fill(min, null);
        }
        while(! queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == hole[0] && p.y == hole[1]) {
                return p.str;
            }
            int x = p.x;
            int y = p.y;
            String str = p.str;
            int[][] dirs = {{-1, 0}, {1,0}, {0, -1}, {0, 1}};
            String[] ds=new String[] {"d","u","l","r"};
            for (int i = 0 ; i < dirs.length ; i ++) {
                int[] dir = dirs[i];
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
                if (newdist < mindist[newx][newy]) { // not visited
                    mindist[newx][newy] = newdist;
                    minstr[newx][newy] = str;
                    queue.offer(new Point(newx, newy, newdist, str + ds[i]));
                }
                else if (newdist == mindist[newx][newy]) {
                    if (str.compareTo(minstr[newx][newy]) < 0) {
                        minstr[newx][newy] = str;
                    }
                }
            }
        }
        return "impossible";
    }
    public class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if(p1.dis != p2.dis) {
                return p1.dis - p2.dis;
            }
            else {
                return p1.str.compareTo(p2.str);
            }
        }
    }

    public boolean reachsBoundary(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) return true;
        else return false;
    }
}
