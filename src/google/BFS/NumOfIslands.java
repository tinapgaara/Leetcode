package google.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yingtan on 10/30/17.
 *
 * 200. Number of Islands
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3
 */
public class NumOfIslands {

    public int numIslands(char[][] grid) {
        if ( (grid == null) || (grid.length == 0) ) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0 ; i < row; i  ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i , j);
                    count ++;
                }
            }
        }
        return count;
    }

    public int numCircumlands(char[][] grid) {
        if ( (grid == null) || (grid.length == 0) ) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0 ; i < row; i  ++) {
            for (int j = 0; j < col; j ++) {
                if (grid[i][j] == '1') {
                    count = count + circumstance(grid, i , j);
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{x,y});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int px = p[0];
            int py = p[1];
            if (px >= 0 && px < grid.length && py >= 0 && py < grid[0].length) {
                if (grid[px][py] == '1') {
                    grid[px][py] = '2';
                    queue.offer(new int[]{px-1, py});
                    queue.offer(new int[]{px+1, py});
                    queue.offer(new int[]{px, py-1});
                    queue.offer(new int[]{px, py+1});
                }
            }
        }
    }

    public int circumstance(char[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{x,y});
        int cir = 0;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int px = p[0];
            int py = p[1];
            if (px >= 0 && px < grid.length && py >= 0 && py < grid[0].length) {
                if (grid[px][py] == '1') {
                    grid[px][py] = '2';
                    queue.offer(new int[]{px-1, py});
                    queue.offer(new int[]{px+1, py});
                    queue.offer(new int[]{px, py-1});
                    queue.offer(new int[]{px, py+1});
                }
                else if (grid[px][py] == '0'){
                    cir ++;
                }
            }
            else {
                cir ++;
            }
        }
        return cir;
    }

    /*
    * 题目要求:
    * 一个matrix包含多个颜色,每个颜色用一个int表示,现在给一个起始坐标,算出与起始坐标颜色相同联通的所有格子的周长
    * 2 2 0
    * 0 3 0
    * 3 3 3
    * 起始点为(1,1)
    * 则周长是 10
    * */
    public int calCircumOfSameColor(int[][] matrix, int startx, int starty) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startx, starty});
        int color = matrix[startx][starty];
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        int cir = 0;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];

            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
                if (! vis[x][y]) {
                    vis[x][y] = true;
                    if (matrix[x][y] == color) {
                        queue.offer(new int[]{x-1, y});
                        queue.offer(new int[]{x+1, y});
                        queue.offer(new int[]{x, y-1});
                        queue.offer(new int[]{x, y+1});
                    }
                }
                // two cases: 1) hit differnt color(can be visited before)
                if (matrix[x][y] != color) {
                    cir ++;
                }
            }
            // 2), out side boundary
            else {
                cir ++;
            }
        }
        return cir;
    }
    /*
        * 题目要求:
        * 一个matrix包含多个颜色,每个颜色用一个int表示,现在给一个起始坐标,算出与起始坐标颜色相同联通的所有格子的周长
        * 2 2 0
        * 0 3 0
        * 3 3 3
        * 起始点为(1,1)
        * 则周长是 10
        *
        * 现在给的是一个int[][] pos 一组起始坐标, 对于每次坐标,开启一个新线程来bfs这个matrix 并且把那个坐标联通的部分的周长算出来
        * 求问怎么多线程实现这个方法
        * */
    public int calCircumOfSameColor_multithread(int[][] matrix, int[][] pos) {
        BlockingQueue<int[]> queue = new ArrayBlockingQueue<int[]>(pos.length);
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        Integer cir = 0;
        calCircumThread[] threads = new calCircumThread[pos.length];
        long waitQueueTime = 1000;
        int i = 0;
        for (int[] p : pos) {
            threads[i] = new calCircumThread(queue, p, matrix, vis, waitQueueTime, cir);
            threads[i].start();
            i ++;
        }
        // when all threads are finished, accumulate the circum
        // check if all cells in matrix are visited
        boolean check = true;
        while(check) {
            for (i = 0 ; i < matrix.length; i ++) {
                for (int j = 0 ; j < matrix[0].length; j ++) {
                    if (! vis[i][j]) {
                        try {
                            Thread.sleep(waitQueueTime);
                        } catch (InterruptedException e) {

                        }
                        break;
                    }
                }
            }
            // finished
            check = false;
        }
        for (i = 0 ; i < pos.length; i ++) {
            threads[i].setFinished();
        }
        return cir;

    }

    public class calCircumThread extends Thread {
        BlockingQueue<int[]> queue;
        int[][] matrix;
        boolean[][] vis;
        int[] start;
        boolean finished;
        Integer cir;
        long waitQueueTime;
        public calCircumThread(BlockingQueue<int[]> queue, int[] startpos,
                               int[][] matrix, boolean[][] vis, long waitQueueTime, int cir) {
            this.queue = queue;
            this.start = startpos;
            this.matrix = matrix;
            this.vis = vis;
            this.waitQueueTime = waitQueueTime;
            finished = false;
            this.cir = cir;
        }
        @Override
        public void run() {
            int color = matrix[start[0]][start[1]];
            while (!finished) {
                while (!queue.isEmpty()) {
                    int[] p = null;
                    // keep waiting until get the node from the poll
                    try {
                        p = queue.poll(waitQueueTime, TimeUnit.MICROSECONDS);
                    } catch (InterruptedException e) {
                        break;
                    }
                    int x = p[0];
                    int y = p[1];
                    if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
                        if (setVisFlag(x, y)) {
                            if (matrix[x][y] == color) {
                                queue.offer(new int[]{x-1, y});
                                queue.offer(new int[]{x+1, y});
                                queue.offer(new int[]{x, y-1});
                                queue.offer(new int[]{x, y+1});
                            }
                        }
                        if (matrix[x][y] != color) {
                            increaseCircum();
                        }
                    }
                    else {
                        increaseCircum();
                    }
                }
                try {
                    Thread.sleep(waitQueueTime);
                } catch (InterruptedException e) {
                    //
                }

            }
        }

        public synchronized boolean setVisFlag(int i, int j) {
            if (! vis[i][j]) {
                vis[i][j] = true;
                return true;
            }
            else {
                return false;
            }
        }

        public synchronized void increaseCircum() {
            this.cir ++;
        }

        public void setFinished() {
            finished = true;
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        grid[0] = new char[]{'1', '0', '0'};
        grid[1] = new char[]{'0', '1', '0'};
        grid[2] = new char[]{'0', '1', '1'};
        List<Integer> l = new ArrayList<>();
        NumOfIslands ob = new NumOfIslands();
        System.out.println(ob.numCircumlands(grid));


        int[][] grid2 = new int[3][3];
        grid2[0] = new int[]{2, 2, 0};
        grid2[1] = new int[]{0, 3, 0};
        grid2[2] = new int[]{3, 3, 3};
        System.out.println(ob.calCircumOfSameColor(grid2, 1, 1));
    }
}
