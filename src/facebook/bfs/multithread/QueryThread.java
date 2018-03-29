package facebook.bfs.multithread;
import java.util.*;
import java.util.concurrent.*;
public class QueryThread extends Thread {
    int[][] matrix;
    BlockingQueue<int[]> queue;
    boolean isRunning = true;
    int color;
    long timeout = 1000000;
    public QueryThread(int[][] matrix, BlockingQueue<int[]> queue, int color) {
        this.matrix = matrix;
        this.queue = queue;
        this.color = color;
    }
    public void run() {
        long lastupdate = 0;
        while(isRunning) {
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int row = matrix.length;
            int col = matrix[0].length;
            //while(! queue.isEmpty()) {
                try {
                    int[] p = queue.poll(timeout, TimeUnit.MILLISECONDS);
                    int i = p[0];
                    int j = p[1];
                    lastupdate = System.currentTimeMillis();
                    if (i >= 0 && i < row && j >= 0 && j < col) {
                        // have not been marked
                        if (matrix[i][j] == 1) {
                            //matrix[i][j] = color; // mark as the current category
                            updateMatrix(matrix, i, j, color);
                            for (int[] dir : dirs) {
                                int newi = i + dir[0];
                                int newj = j + dir[1];
                                queue.offer(new int[]{newi, newj});
                            }
                        }
                    }
                } catch(InterruptedException e) { // queue is empty
                    //ignore
                    // wait enough long time, so the task is finished.
                    if (System.currentTimeMillis() - lastupdate > 10000000) {
                        // nothing new
                        isRunning = false;
                    }
                    // nothing in the queue, child thread need to sleep
                    else {
                        try {
                            Thread.sleep(10000); // sleep sometime
                        } catch(InterruptedException en) {
                            // ignore
                        }
                    }
                }

            //}
        }
    }
    public void stopThread() {
        isRunning = false;
    }
    public boolean isStop() {
        return isRunning;
    }
    public synchronized void updateMatrix(int[][] matrix, int i, int j, int color) {
        matrix[i][j] = color;
    }
}
