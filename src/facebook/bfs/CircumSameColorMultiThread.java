package facebook.bfs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yingtan on 3/18/18.
 */
public class CircumSameColorMultiThread {
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
}
