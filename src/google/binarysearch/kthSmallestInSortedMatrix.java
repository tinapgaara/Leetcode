package google.binarysearch;

/**
 * Created by yingtan on 1/30/17.
 *
 * 378. Kth Smallest Element in a Sorted Matrix   Add to List QuestionEditorial Solution  My Submissions
 Total Accepted: 26337
 Total Submissions: 60667
 Difficulty: Medium
 Contributors: Admin
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
 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class kthSmallestInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;

        int lowNum = matrix[0][0];
        int highNum = matrix[col-1][col-1];

        while (lowNum < highNum) {
            int midNum = lowNum + ((highNum - lowNum) >> 1) ;
            int count = countSmaller(matrix, midNum); // how many nums smaller than midNum

            if (count < k) {
                lowNum = midNum + 1; // find larger element
            }
            else {
                highNum = midNum;// important !!!!  for case count == k
            }
        }
        return highNum;
    }

    public int countSmaller(int[][] matrix, int target) {
        int row = 0;
        int col = matrix.length - 1;
        int count = 0;
        while ( (row < matrix.length) && (col >= 0) ) {
            int num = matrix[row][col];
            if (num <= target) { // important !: num <= target
                count = count + col + 1; // important ! col + 1
                row ++;
            }
            else {
                col --;
            }
        }
        return count;
    }
}
