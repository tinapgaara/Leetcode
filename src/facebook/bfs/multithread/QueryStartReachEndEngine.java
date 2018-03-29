package facebook.bfs.multithread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
/*
*
* 一个m*n的matrix， 然后只有0，1，有些地方不能走（标记为0），有些可以走（标记为1）
给你一个开始位置，一个结束位置，问能不能走到。如果需要最短路径的话就是bfs。ollow up是要检查好多位置的话，
预处理就是先把每个点可以走到的算好（比如数岛那个题的话），就是每个岛，都标记成不同的数字，那么同一个岛上的就可以走到
* */
public class QueryStartReachEndEngine {
    // Initial Question: can reach simple
    public boolean canReach(int[][] matrix, int start_i, int start_j, int end_i, int end_j) {
        if (matrix == null || matrix.length == 0)return true;
        Queue<int[]> queue = new LinkedList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new int[]{start_i, start_j});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            if (i == end_i && j == end_j) {
                return true;
            }
            if (i >= 0 && i < row && j >= 0 && j < col) {
                // use 2 as vis flag
                if (matrix[i][j] == 1) {
                    matrix[i][j] = 2; // mark as vis
                    for (int[] dir : dirs) {
                        int newi = i + dir[0];
                        int newj = j + dir[1];
                        queue.offer(new int[]{newi, newj});
                    }
                }
            }
        }
        return false;
    }
    // Follow up 1: what if the query be called frequently, given a input start point and end point, check if can reach? : preprocess, categorize
    public QueryStartReachEndEngine(int[][] matrix) {
        preprocess(matrix);
    }
    public boolean canReachQuery(int[][] matrix, int start_i, int start_j, int end_i, int end_j) {
        if (matrix == null || matrix.length == 0)return true;
        //preprocess(matrix);
        if (matrix[start_i][start_j] == matrix[end_i][end_j]) {
            // same color, same category
            return true;
        }
        return false;
    }
    public void preprocess(int[][] matrix) {
        // categorize the matrix as several islands. Inside each island, those nodes can be reachable from each other
        int row = matrix.length;
        int col = matrix[0].length;
        int category_enum = 2; // enum starts with integer 2.
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (matrix[i][j] == 1) {
                    // can go through and have not been categorized
                    bfs(i, j, matrix, category_enum);
                    category_enum ++;
                }
            }
        }
    }
    public void bfs(int start_i, int start_j , int[][] matrix, int color) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start_i, start_j});
        int row = matrix.length;
        int col = matrix[0].length;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            if (i >= 0 && i < row && j >= 0 && j < col) {
                // have not been marked
                if (matrix[i][j] == 1) {
                    matrix[i][j] = color; // mark as the current category
                    for (int[] dir : dirs) {
                        int newi = i + dir[0];
                        int newj = j + dir[1];
                        queue.offer(new int[]{newi, newj});
                    }
                }
            }
        }
    }
    // Follow up 2: what if multiple queries happen at the same time? multiple threads to preprocess ? -> multithread_bfs
    public void preprocess_multithread(int[][] matrix) {
        // categorize the matrix as several islands. Inside each island, those nodes can be reachable from each other
        int row = matrix.length;
        int col = matrix[0].length;
        int category_enum = 2; // enum starts with integer 2.
        QueryThread[] threads = new QueryThread[10];
        BlockingQueue<int[]> queue = new LinkedBlockingQueue<>();
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (matrix[i][j] == 1) {
                    // can go through and have not been categorized
                    // have many threads share the same matrix, and blockingqueue
                    queue.offer(new int[]{i, j});
                    for (int k = 0 ; k < 10; k ++) {
                        threads[k] = new QueryThread(matrix, queue, category_enum);
                        threads[k].start();
                        //bfs(i, j, matrix, category_enum);
                    }
                    // make sure all threads finished marking cells this color
                    while(true) {
                        boolean isBreak = true;
                        for (int k = 0 ; k < 10; k ++) {
                            if (! threads[k].isStop()) {
                                isBreak = false;
                                try {
                                    Thread.sleep(100000); // give CPU to child threads
                                } catch(InterruptedException e) {

                                }
                            }
                        }
                        if (isBreak) {
                            break;
                        }
                    }
                    // if 10 threads all finished without new data, then, exit and: category++
                    category_enum ++;
                }
            }
        }
    }
}
