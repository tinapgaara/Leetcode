package facebook.array;

/**
 * Created by yingtan on 3/21/18.
 *
 * 04. Range Sum Query 2D - Immutable
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Range Sum Query 2D
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 */
public class RangeSumQuery2D {
    int[][] colsum;
    public RangeSumQuery2D(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            // nothing to do
            return;
        }
        colsum = new int[matrix.length][matrix[0].length];
        for (int i = 0 ; i < matrix.length; i ++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j ++) {
                sum = sum + matrix[i][j];
                colsum[i][j] = sum;
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i ++) {
            if (col1 > 0) {
                sum = sum + colsum[i][col2] - colsum[i][col1 - 1];
            }
            else {
                sum = sum + colsum[i][col2];
            }
        }
        return sum;
    }
}
