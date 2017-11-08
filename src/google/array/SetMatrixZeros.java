package google.array;

/**
 * Created by yingtan on 5/6/17.
 *
 * 73. Set Matrix Zeroes
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.


 */
public class SetMatrixZeros {

    public void setZeroes(int[][] matrix) {

        // reuse the first row and the first column
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col ; j ++) {
                if (matrix[i][j] == 0 ) {
                    if (i == 0) firstRowHasZero = true;
                    if (j == 0) firstColHasZero = true;

                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowHasZero) {
            for (int j = 0 ; j < col; j ++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColHasZero) {
            for (int i = 0 ; i < row; i ++) {
                matrix[i][0] = 0;
            }
        }

    }
}
