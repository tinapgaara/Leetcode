package DFSBFS;

/**
 * Created by yingtan on 8/7/16.
 */
public class LongestIncreatingMatrix {

    private int[] dirX = {0, 1, 0, -1};
    private int[] dirY = {1, 0, -1, 0};

    // down to top, using isVisitedFlag
    public int longestIncreasingPath2(int[][] matrix) {
        if ( (matrix == null) || (matrix.length == 0) )
            return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int maxLen = 0;

        int[][] longestPathFromCurNode = new int[row][col];
        for (int i = 0 ; i < row ; i ++) {
            for (int j = 0; j < col; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j, longestPathFromCurNode));
            }
        }
        return maxLen;

    }

    public int dfs(int[][] matrix, int x, int y, int[][] longestPathFromCurNode) {
        if (longestPathFromCurNode[x][y] != 0)
            return longestPathFromCurNode[x][y];

        for (int i = 0 ; i < 4; i ++) {
            int curX = x + dirX[i];
            int curY = y + dirY[i];

            if ( (curX >= 0) && (curX < longestPathFromCurNode.length)
                    && (curY >= 0) && (curY < longestPathFromCurNode[0].length) ) {
                if (matrix[curX][curY] > matrix[x][y]) {
                    longestPathFromCurNode[x][y] = Math.max(longestPathFromCurNode[x][y],
                                                            dfs(matrix, curX, curY, longestPathFromCurNode));
                }

            }
        }
        return longestPathFromCurNode[x][y] + 1;
    }

    // Time exceeds limit
    public int longestIncreasingPath(int[][] matrix) {
        if ( (matrix == null) || (matrix.length == 0) )
            return 0;
        int row = matrix.length;
        int col = matrix[0].length;

        // boolean[][] isVisited = new boolean[row][col];

        int maxLen = 0;
        boolean[][] isVisited = new boolean[row][col];
        for (int i = 0 ; i < row ; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (! isVisited[i][j]) {
                    isVisited[i][j] = true;
                }
                int curVal = matrix[i][j];
                maxLen = Math.max(maxLen, dfs(matrix, isVisited, i-1, j, 1, curVal) );
                maxLen = Math.max(maxLen, dfs(matrix, isVisited, i, j-1, 1, curVal) );
                maxLen = Math.max(maxLen, dfs(matrix, isVisited, i+1, j, 1, curVal) );
                maxLen = Math.max(maxLen, dfs(matrix, isVisited, i, j+1, 1, curVal) );

                isVisited[i][j] = false;
            }
        }
        return maxLen;
    }

    public int dfs(int[][] matrix, boolean[][] isVisited, int curX, int curY, int depth,
                   int parentVal) {

        int row = matrix.length;
        int col = matrix[0].length;

        if ( (curX < 0) || (curX >= row) || (curY < 0) || (curY >= col) )
            return depth;

        if (isVisited[curX][curY]) {
            return depth;
        }

        int curVal = matrix[curX][curY];

        if (curVal <= parentVal) {
            return depth;
        }

        isVisited[curX][curY] = true;

        //  find next available larger val and dfs
        int maxLen = depth + 1;
        maxLen = Math.max(maxLen,
                dfs(matrix, isVisited, curX-1,  curY, depth + 1, curVal));

        maxLen = Math.max(maxLen,
                dfs(matrix, isVisited, curX+1,  curY, depth + 1, curVal));

        maxLen = Math.max(maxLen,
                dfs(matrix, isVisited, curX,  curY-1, depth + 1, curVal));

        maxLen = Math.max(maxLen,
                dfs(matrix, isVisited, curX,  curY+1, depth + 1, curVal));

        isVisited[curX][curY] = false;

        return maxLen;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3,4,5}, {3,2,6}, {2,2,1}};
        LongestIncreatingMatrix ob = new LongestIncreatingMatrix();
        System.out.println(ob.longestIncreasingPath(matrix));
    }
}
