package google.binarysearch;

/**
 * Created by yingtan on 8/20/17.
 *
 * 74. Search a 2D Matrix
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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

    // Time  complexity:log(mn)
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        // treat 2D matrix this as an 1D sorted array
        int start = 0;
        int end = m * n - 1;

        while(start <= end){
            int mid = start+(end-start)/2;
            int midX = mid / n;
            int midY = mid % n;

            if(matrix[midX][midY] == target)
                return true;

            if(matrix[midX][midY] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        return false;
    }
}
