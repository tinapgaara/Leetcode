package facebook.array;
/*
*
*
* 3.给一个2d matrix，每个里面值要么是1要么是0， 假如出现1，后面的数都是1.
找出最左边是1的列
[[0, 0, 1, 1, 1],
[0, 1, 1, 1, 1],
[0, 0, 1, 1, 1],
[0, 0, 0, 0, 0]]
binarysearch classic
greedy

* */
public class FindFirstVertialOnes {
    public int findFirstColumn_greedy(int[][] matrix) {
        // greedy way
        int j = 0;
        for (int i = 0; i < matrix.length; i ++) {
            if (matrix[i][j] == 0) {
                // invalid
                j ++;
            }
            else {
                //valid
                // keep searching the down ones
                i ++;
            }
        }
        if (j < matrix[0].length) {
            return j;
        }
        else {
            return -1;
        }
    }
    // logcol * row
    public int findFirstCol_binarySearch(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int low = 0;
        int high = col - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (valid(matrix, mid)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        if (valid(matrix , low)) return low;
        if (valid(matrix, high)) return high;
        return -1;
    }
    public boolean valid(int[][] matrix, int colIndex) {
        for (int i = 0 ; i < matrix.length; i ++) {
            if (matrix[i][colIndex] != 1) {
                return false;
            }
        }
        return true;
    }
}
