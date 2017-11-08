package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/6/15.
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder_2(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null) return res;

        int row = matrix.length;
        if (matrix.length == 0) return res;

        int col = matrix[0].length;

        int x = 0;
        int y = 0;

        while (row > 0 && col > 0) {
            if (row == 1) {
                // only one row left
                for (int j = 0 ; j < col; j ++) {
                    res.add(matrix[x][y]);
                    y ++;
                }
                break;
            }
            else if (col == 1) {
                // only one col left
                for (int i = 0 ; i < row; i ++) {
                    res.add(matrix[x][y]);
                    x ++;
                }
                break;
            }
            // move right
            for (int j = 0 ; j < col - 1; j ++) { // important col -1
                res.add(matrix[x][y]);
                y ++;
            }
            // move down
            for (int i = 0 ; i < row - 1; i ++) {// important row -1
                res.add(matrix[x][y]);
                x ++;
            }
            // move left
            for (int j = col-1 ; j > 0;  j --) { // important >0
                res.add(matrix[x][y]);
                y --;
            }
            // move top
            for (int i = row-1 ; i > 0;  i --) { // important >0
                res.add(matrix[x][y]);
                x --;
            }

            row = row - 2;
            col = col - 2;

            x ++;
            y ++;
        }
        return res;

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null) return res;

        int row = matrix.length;
        if (matrix.length == 0) return res;

        int col = matrix[0].length;

        boolean[][] visitedFlags = new boolean[row][col];

        int i = 0;
        int j = 0;
        int dir = 0; //0:right, 1:down, 2:left, 3:up
        while(res.size() < row*col) {
            if(dir == 0) {
                while((j < col) && (!visitedFlags[i][j])) {
                    res.add(matrix[i][j]);
                    visitedFlags[i][j] = true;
                    j++;
                }
                dir = 1;
                j --;
                i ++;
            }
            else if (dir == 1) {
                while((i < row) && (!visitedFlags[i][j])) {
                    res.add(matrix[i][j]);
                    visitedFlags[i][j] = true;
                    i++;
                }
                dir = 2;
                i --;
                j --;
            }
            else if (dir == 2) {
                while((j >= 0) && (!visitedFlags[i][j])) {
                    res.add(matrix[i][j]);
                    visitedFlags[i][j] = true;
                    j--;
                }
                dir = 3;
                j ++;
                i --;
            }
            else {
                while((i >= 0) && (!visitedFlags[i][j])) {
                    res.add(matrix[i][j]);
                    visitedFlags[i][j] = true;
                    i--;
                }
                dir = 0;
                i ++;
                j ++;
            }
        }
        return res;
    }

}
