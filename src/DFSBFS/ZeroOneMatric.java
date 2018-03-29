package DFSBFS;

/**
 * Created by yingtan on 2/25/18.
 *
 * 542. 01 Matrix
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

 The distance between two adjacent cells is 1.
 Example 1:
 Input:

 0 0 0
 0 1 0
 0 0 0
 Output:
 0 0 0
 0 1 0
 0 0 0
 Example 2:
 Input:

 0 0 0
 0 1 0
 1 1 1
 Output:
 0 0 0
 0 1 0
 1 2 1

 */
import java.util.*;
public class ZeroOneMatric {

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0)return matrix;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0 ; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        bfs_fromZero(matrix, queue);
        return matrix;
    }
    public void bfs_fromZero(int[][] matrix, Queue<int[]> queue) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            for (int[] dir : dirs) {
                int newi = i + dir[0];
                int newj = j + dir[1];
                if (newi >= 0 && newi < matrix.length && newj >= 0 && newj < matrix[0].length) {
                    // not vis before and have not been updated
                    if (! vis[newi][newj] && matrix[newi][newj] == 1) {
                        // Important !!! since we update matrix[newi][newj], so we need to set vis here instead of outside loop.
                        vis[newi][newj] = true;
                        matrix[newi][newj] = matrix[i][j] + 1;
                        queue.offer(new int[]{newi, newj});
                    }
                }
            }
        }
    }
    // Solution 1: bfs : Time Exceed Limit !!!! Start from non-zero entry
    public int[][] bfs_fromOne(int[][] matrix) {
        for (int i = 0 ; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                if (matrix[i][j] != 0) {
                    bfs_slow(matrix, i, j);
                }
            }
        }
        return matrix;
    }
    public void bfs_slow(int[][] matrix, int start_i, int start_j) {
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start_i, start_j, 0});
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            int dist = p[2];
            vis[i][j] = true;
            for (int[] dir : dirs) {
                int newi = i + dir[0];
                int newj = j + dir[1];
                if (newi >= 0 && newi < matrix.length && newj >= 0 && newj < matrix[0].length) {
                    if (! vis[newi][newj]) {
                        if (matrix[newi][newj] == 0) {
                            // hit zero
                            matrix[start_i][start_j] = dist + 1;
                            return;
                        }
                        else {
                            queue.offer(new int[]{newi, newj, dist + 1});
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ZeroOneMatric ob = new ZeroOneMatric();
        int[][] board = {{0,1,0}, {0,1,0}, {0,1,0}, {0,1,0},{0,1,0}};
        System.out.println(ob.updateMatrix(board));
    }
}
