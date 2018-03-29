package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 74. Search a 2D Matrix
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 For example,

 Consider the following matrix:

 [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 Given target = 3, return true.
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int i = 0;
        int j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0) {
            if (target > matrix[i][j]) {
                i ++;
            }
            else if (target < matrix[i][j]) {
                j --;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
