package google.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 7/31/17.
 */
public class PacificAtlantic {

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<int[]>();
        if ( (matrix == null) || (matrix.length == 0) ) return res;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        /*
        我们从边缘当作起点开始遍历搜索，然后标记能到达的点位true，分别标记出pacific和atlantic能到达的点，那么最终能返回的点就是二者均为true的点
        */
        for (int i = 0 ; i < row; i ++) {
            // left most col and right most col
            dfs(i, 0, matrix, pacific, Integer.MIN_VALUE);
            dfs(i, col -1, matrix, atlantic, Integer.MIN_VALUE);
        }

        for (int i = 0 ; i < col; i ++) {
            // up most row and down most row
            dfs(0, i, matrix, pacific, Integer.MIN_VALUE);
            dfs(row - 1, i, matrix, atlantic, Integer.MIN_VALUE);
        }

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }

        return res;
    }


    public void dfs(int x, int y, int[][] matrix, boolean[][] vis, int prevVal) {
        if ( (x >= 0) && (x < matrix.length) && (y >= 0) && (y < matrix[0].length) ) {
            if (! vis[x][y] && matrix[x][y] >= prevVal) {
                // important ! vis = true must make sure matrix[x][y] >= prevVal, water can flow here
                vis[x][y] = true;
                dfs(x+1, y, matrix, vis, matrix[x][y]);
                dfs(x-1, y, matrix, vis, matrix[x][y]);
                dfs(x, y+1, matrix, vis, matrix[x][y]);
                dfs(x, y-1, matrix, vis, matrix[x][y]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        PacificAtlantic ob = new PacificAtlantic();
        ob.pacificAtlantic(arr);
    }
}
