package google.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* 给一个矩阵，某些点有守卫，求哪个点离所有守卫都最远。要求尽可能优化
*
* 317. Shortest Distance from All Buildings   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 12712
Total Submissions: 38189
Difficulty: Hard
Contributors: Admin
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
* */
public class ShortestDistanceFromAllBuildings {

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

    public int shortestDistance3(int[][] grid) {
        if ( (grid == null) || (grid.length == 0) )
            return 0;

        int row = grid.length;
        int col = grid[0].length;

        // total dist between x,y and all other buildings
        int[][] distance = new int[row][col];

        // how many buildings can (x, y) reach
        int[][] num = new int[row][col];
        int totalBuildings = 0;
        Queue<Point> queue = new LinkedList<Point>();
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == 1) {
                    queue.offer(new Point(i, j, 0));
                    totalBuildings ++;
                }
            }
        }
        bfs(queue, distance, grid, num);
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == 0 && distance[i][j] != 0 && num[i][j] == totalBuildings)
                    min = Math.min(min, distance[i][j]);
            }
        }
        if (min < Integer.MAX_VALUE)
            return min;
        else
            return -1;
    }

    public void bfs(Queue<Point> queue, int[][] distance, int[][] grid, int[][] num) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        while (! queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            int dis = p.dist;
            vis[x][y] = true;
            // update distance
            distance[x][y] = distance[x][y] + dis;
            System.out.println("set x:" + x + ", y:" + y + "distance:" + distance[x][y]);

            int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
            for (int[] dir: dirs) {
                int newx = x + dir[0];
                int newy = y + dir[1];
                if ( (newx >= 0) && (newx < distance.length) && (newy >= 0) && (newy < distance[0].length) ) {
                    if (! vis[newx][newy] && grid[newx][newy] == 0) {
                        queue.offer(new Point(newx, newy, dis + 1));
                        num[newx][newy] ++;
                    }
                }
            }
        }
    }

    public int shortestDistance_2(int[][] grid) {
        if ( (grid == null) || (grid.length == 0) )
            return 0;

        int row = grid.length;
        int col = grid[0].length;

        // total dist between x,y and all other buildings
        int[][] distance = new int[row][col];

        // how many buildings can (x, y) reach
        int[][] num = new int[row][col];
        int totalBuildings = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == 1) {
                    bfs(i, j, distance, grid, num);
                    totalBuildings ++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == 0 && distance[i][j] != 0 && num[i][j] == totalBuildings)
                    min = Math.min(min, distance[i][j]);
            }
        }
        if (min < Integer.MAX_VALUE)
            return min;
        else
            return -1;
    }

    public void bfs(int i, int j, int[][] distance, int[][] grid, int[][] num) {
        Queue<Point> queue = new LinkedList<Point>();
        // the entry point is 1, need to ignore this and proceed to push 4 cells around it
        queue.offer(new Point(i+1, j, 1));
        queue.offer(new Point(i-1, j, 1));
        queue.offer(new Point(i, j+1, 1));
        queue.offer(new Point(i, j-1, 1));

        boolean[][] vis = new boolean[grid.length][grid[0].length];

        while (! queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            int dis = p.dist;

            if ( (x >= 0) && (x < distance.length) && (y >= 0) && (y < distance[0].length) && (! vis[x][y]) && (grid[x][y] == 0))             {
                vis[x][y] = true;
                // update distance
                distance[x][y] = distance[x][y] + dis;
                // update number of buildings (x, y) can reach
                num[x][y] ++;

                // expand to other 4 cells
                queue.offer(new Point(x+1, y, dis + 1));
                queue.offer(new Point(x-1, y, dis + 1));
                queue.offer(new Point(x, y+1, dis + 1));
                queue.offer(new Point(x, y-1, dis + 1));
            }
        }
    }


    public int shortestDistance(int[][] grid) {
        if ( (grid == null) || (grid[0] == null) ) return -1;
        int row = grid.length;
        int col = grid[0].length;

        int[][] distGrid = new int[row][col];
        int[][] reach = new int[row][col]; // how many 1 this cell can reach
        int buildingNum = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (grid[i][j] == 1) {
                    buildingNum ++;
                    boolean[][] visitedFlags = new boolean[row][col];
                    BFS(grid, i, j, visitedFlags, distGrid, reach);
                }
            }
        }
        // cal min
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col ; j ++) {
                if ( (distGrid[i][j] != -1) && (grid[i][j] != 1) && (reach[i][j] == buildingNum) ) {
                    min = Math.min(min, distGrid[i][j]);
                }
            }
        }
        // no result
        if (min == Integer.MAX_VALUE)
            return -1;
        return min;

    }

    public void BFS(int[][] grid, int x, int y, boolean[][] ifVisit, int[][] distGrid, int[][] reach) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(x, y, 0));
        while (! queue.isEmpty()) {
            Point cur = queue.poll();
            int i = cur.x;
            int j = cur.y;
            int dist = cur.dist;
            if ( (i >= 0) && (i < grid.length) && (j >= 0) && (j < grid[0].length) ) {
                if (! ifVisit[i][j]) {
                    ifVisit[i][j] = true;
                    // obstacle
                    if (grid[i][j] == 2) distGrid[i][j] = -1;
                        // can go through | or starting point
                    else if ( (grid[i][j] == 0) || (dist == 0) ) {
                        reach[i][j] ++;
                        distGrid[i][j] = distGrid[i][j] + dist;
                        queue.offer(new Point(i + 1, j, dist + 1));
                        queue.offer(new Point(i - 1, j, dist + 1));
                        queue.offer(new Point(i, j + 1, dist + 1));
                        queue.offer(new Point(i, j - 1, dist + 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ShortestDistanceFromAllBuildings ob = new ShortestDistanceFromAllBuildings();
        int[][] grid = new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        int[][] grid2 = new int[][]{{1,1,1,1,1,0},{0,0,0,0,0,1},{0,1,1,0,0,1},{1,0,0,1,0,1},{1,0,1,0,0,1},{1,0,0,0,0,1},{0,1,1,1,1,0}};
        int[][] grid3 = new int[][]
                {{1,1,1,1,1,1,1,0},{0,0,0,0,0,0,0,1},{1,1,1,1,1,1,0,1},{1,0,0,0,1,1,0,1},
                        {1,0,1,1,1,1,0,1},{1,0,1,0,0,1,0,1},{1,0,1,1,1,1,0,1},{1,0,0,0,0,0,0,1},{0,1,1,1,1,1,1,0}};
        System.out.println(ob.shortestDistance3(grid));
    }
}
