package search;

/**
 * Created by yingtan on 8/7/16.
 *
 *
 378. Kth Smallest Element in a Sorted Matrix  QuestionEditorial Solution  My Submissions
 Total Accepted: 3968
 Total Submissions: 9783
 Difficulty: Medium
 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.

 *
 */
public class kthSmallestMatrixSearch {
    public int kthSmallest(int[][] matrix, int k) {
        if ( (matrix == null) || (matrix[0].length == 0) ) return 0;

        int row = matrix.length;
        int col = matrix[0].length;

        int left = matrix[0][0];
        int right = matrix[row-1][col-1];

        while (left < right) {
            int mid = (left + right) >> 1;
            int numSmallerThan = 0;
            numSmallerThan = numSmallerThan + countSmallerOnes(matrix, mid, col);
            if (numSmallerThan < k)
                left = mid + 1;
            else
                right = mid; // TODO ??
        }
        return left;
    }

    public int countSmallerOnes(int[][] matrix, int val, int colSize) {
        int rowIndex = matrix.length - 1;
        int colIndex = 0;

        int count = 0;

        while ( (rowIndex >= 0) && (colIndex < colSize) ) {
            int comparedVal = matrix[rowIndex][colIndex];
            if (comparedVal <= val) {
                colIndex ++;
                count = count + rowIndex + 1;
            }
            else {
                rowIndex --;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,3,17}, {4,16,18}, {19,21,30}};
        kthSmallestMatrixSearch ob = new kthSmallestMatrixSearch();
        ob.kthSmallest(matrix,  4);
    }
}
