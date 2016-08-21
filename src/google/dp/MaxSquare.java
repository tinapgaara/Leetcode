package google.dp;

/**
 * Created by yingtan on 12/22/15.
 */
public class MaxSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null) return 0;
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;

        int[][] edges = new int[row][col];
        int maxEdgeNum = 0;
        for (int i = 0 ; i < edges.length; i ++) {
            if (matrix[i][0] == '1') {
                edges[i][0] = 1;
                maxEdgeNum = 1;
            }
        }
        for (int i = 0; i < edges[0].length; i ++) {
            if (matrix[0][i] == '1') {
                edges[0][i] = 1;
                maxEdgeNum = 1;
            }
        }

        for (int i = 1; i < edges.length; i ++) {
            for (int j = 1; j < edges[0].length; j ++) {
                if (matrix[i][j] == '1') {
                    int newEdgeNum
                            = Math.min(edges[i-1][j-1], Math.min(edges[i-1][j], edges[i][j-1])) + 1;
                    edges[i][j] = newEdgeNum;
                    maxEdgeNum = Math.max(maxEdgeNum, newEdgeNum);
                }
            }
        }
        return maxEdgeNum * maxEdgeNum;
    }
}
