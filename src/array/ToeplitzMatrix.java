package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 766. Toeplitz Matrix
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

 Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


 Example 1:

 Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 Output: True
 Explanation:
 1234
 5123
 9512

 In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]", and in each diagonal all elements are the same, so the answer is True.
 Example 2:

 Input: matrix = [[1,2],[2,2]]
 Output: False
 Explanation:
 The diagonal "[1, 2]" has different elements.
 */
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return true;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        Integer num = matrix[0][0];
        // diagonal line
        // very Important !!!to use Math.min(row, col)
        for (int i = 0; i < Math.min(row, col); i ++) {
            if(matrix[i][i] != num) {
                return false;
            }
        }
        // right-top cornor
        for (int delta = col - 2; delta >= 1; delta --) {
            num = null; // very IMPORTANT !!!!
            // scan each line
            for (int y = col - 1; y >= delta; y --) {
                int x = y - delta;
                if (x >= 0 && x < row) { // very IMPORTANT !!!!!
                    if (num == null) {
                        num = matrix[x][y];
                    }
                    else {
                        if (matrix[x][y] != num) {
                            return false;
                        }
                    }
                }
            }
        }
        // left-buttom-cornor
        for (int delta = row - 2; delta >= 1; delta -- ) {
            num = null; // very IMPORTANT !!!!
            // scan each line
            for (int x = row - 1; x >= delta; x --) {
                int y = x - delta;
                if (y >= 0 && y < col) { // very IMPORTANT !!!!! for case [10,20,10]
                    if (num == null) {
                        num = matrix[x][y];
                    }
                    else {
                        if (matrix[x][y] != num) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
