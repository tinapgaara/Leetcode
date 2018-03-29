package graph;

/**
 * Created by yingtan on 1/18/18.
 */
import java.util.*;
public class PaintBooleanMatrix {

    public void paint(boolean[][] matrix, int x, int y) {
        // bfs
        boolean org = matrix[x][y];
        matrix[x][y] = !matrix[x][y]; // flip start point
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int i = p[0];
            int j = p[1];
            if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
                if (matrix[i][j] == org) {
                    // need to flip
                    matrix[i][j] = ! matrix[i][j];// flip
                    queue.offer(new int[]{i+1, j});
                    queue.offer(new int[]{i-1, j});
                    queue.offer(new int[]{i, j+1});
                    queue.offer(new int[]{i, j-1});
                }
            }
        }
    }
}
