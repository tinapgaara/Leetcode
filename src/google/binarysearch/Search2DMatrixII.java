package google.binarysearch;

/**
 * Created by yingtan on 8/20/17.
 *
 * 240. Search a 2D Matrix II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 For example,

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.
 */
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        // starting from right top point
        //   利用的性质，right top point， the points left must be smaller than the current point, points buttom must be larger than the current point
        //  or can start from left buttom
        int x = 0;
        int y = col - 1;
        while (x < row && y >= 0) {
            if (matrix[x][y] < target) {
                x ++;
            }
            else if (matrix[x][y] > target) {
                y --;
            }
            else return true;
        }
        return false;
    }
}
