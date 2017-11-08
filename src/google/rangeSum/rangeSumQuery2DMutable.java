package google.rangeSum;

/**
 * Created by yingtan on 7/20/17.
 * 308. Range Sum Query 2D - Mutable
 *
 * Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 update(3, 2, 2)
 sumRegion(2, 1, 4, 3) -> 10
 */
public class rangeSumQuery2DMutable {

    public class NumMatrix {
        int[][] rowSums;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0)
                return;
            rowSums = new int[matrix.length][matrix[0].length];

            // 建rowSums矩阵 将(x,y)和(x, y-1)的和加在一起
        /*
         Given matrix = [
          [3, 0, 1, 4, 2],
          [5, 6, 3, 2, 1],
          [1, 2, 0, 1, 5],
          [4, 1, 0, 1, 7],
          [1, 0, 3, 0, 5]
        ]

        =>
          rowSums = [
          [3, 3, 4, 8, 10],
          [5, 11,14, 16, 17],
          [1, 3, 3, 4, 9],
          [4, 5, 5, 6, 13],
          [1, 1, 4, 4, 9]
        ]
        then,
        matrix[row][col] = rowSums[row][col] - rowSums[row][col-1];

        */
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    rowSums[i][j] = matrix[i][j] + (j == 0 ? 0 : rowSums[i][j - 1]);
                }
            }
        }

        public void update(int row, int col, int val) {
            // 求出新值与旧值的差
            // calculate diff between rowSums[row][col] with rowSums[row][col-1] == matrix[row][col]
            int diff = val - (rowSums[row][col] - (col == 0 ? 0 : rowSums[row][col - 1]));

            // 更新该行受影响的sum: o(cols)
            for (int j = col; j < rowSums[0].length; j++) {
                rowSums[row][j] += diff;
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;

            // 逐行求和，每行的相应和为两sum相减
            // o(rows)
            for (int i = row1; i <= row2; i++) {
                res += rowSums[i][col2] - (col1 == 0 ? 0 :rowSums[i][col1 - 1]);
            }
            return res;
        }
    }
}
