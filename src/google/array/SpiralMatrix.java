package google.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/6/15.
 */
public class SpiralMatrix {

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


    public int[][] generateMatrix(int n) {
        int lastElement = n * n ;

        int[][] res = new int[n][n];
        int i = 0;
        int j = 0;
        int dir = 0; //0:right, 1:down, 2:left, 3:up
        int curVal = 1;
        while(curVal <= n * n) {
            if(dir == 0) {
                while((j < n) && (res[i][j] == 0)) {
                    res[i][j] = curVal;
                    j++;
                    curVal++;
                }
                dir = 1;
                j --;
                i ++;
            }
            else if (dir == 1) {
                while((i < n) && (res[i][j] == 0)) {
                    res[i][j] = curVal;
                    i++;
                    curVal ++;
                }
                dir = 2;
                i --;
                j --;
            }
            else if (dir == 2) {
                while((j >= 0) && (res[i][j] == 0)) {
                    res[i][j] = curVal;
                    j--;
                    curVal ++;
                }
                dir = 3;
                j ++;
                i --;
            }
            else {
                while((i >= 0) && (res[i][j] == 0)) {
                    res[i][j] = curVal;
                    i--;
                    curVal ++;
                }
                dir = 0;
                i ++;
                j ++;
            }
        }
        return res;

    }
}
