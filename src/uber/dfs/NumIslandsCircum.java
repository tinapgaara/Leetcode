package uber.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/8/17.
 * numof islands 求围的周长
 */
public class NumIslandsCircum {

    private List<Integer> landsCircum(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0 ; i < matrix.length; i ++) {
            for (int j = 0 ; j < matrix[0].length ; j ++) {
                int[] circum = new int[1];
                if (matrix[i][j] == 1) {
                    // init: 4 edges
                    circum[0] = 2;
                    dfs(matrix, i, j, circum);
                    res.add(circum[0]);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int i, int j, int[] circum) {
        if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            if(matrix[i][j] == 1) {
                circum[0] = circum[0] + 4 - 2;
                // mark as visited
                matrix[i][j] = 2;
                dfs(matrix, i-1, j, circum);
                dfs(matrix, i+1, j, circum);
                dfs(matrix, i, j-1, circum);
                dfs(matrix, i, j+1, circum);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix= {{1,1,0}, {0,0,1}, {1,1,1}};
        NumIslandsCircum ob = new NumIslandsCircum();
        System.out.println(ob.landsCircum(matrix));
    }

}
