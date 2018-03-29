package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 */
public class KthSmallestElementInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;

        int lowNum = matrix[0][0];
        int highNum = matrix[col-1][col-1];

        while (lowNum < highNum) {
            int midNum = lowNum + (highNum - lowNum) / 2;
            int count = countSmaller(matrix, midNum);
            if (count < k) {
                lowNum = midNum + 1;
            }
            else {
                highNum = midNum;
            }
        }
        return highNum;
    }

    public int countSmaller(int[][] matrix, int number) {
        int colLen = matrix[0].length;
        int rowLen = matrix.length;
        int i = 0;
        int j = matrix[0].length - 1;
        int count = 0;
        while(i < matrix.length && j >= 0) {
            if (matrix[i][j] <= number) {
                count = count + colLen;
                i ++;
            }
            else if (matrix[i][j] > number) {
                j --;
            }
        }
        return count;
    }
}
