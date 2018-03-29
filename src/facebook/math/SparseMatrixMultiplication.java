package facebook.math;

/**
 * Created by yingtan on 12/18/17.
 *
 * 311. Sparse Matrix Multiplication
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given two sparse matrices A and B, return the result of AB.

 You may assume that A's column number is equal to B's row number.

 Example:

 A = [
 [ 1, 0, 0],
 [-1, 0, 3]
 ]

 B = [
 [ 7, 0, 0 ],
 [ 0, 0, 0 ],
 [ 0, 0, 1 ]
 ]


       |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 AB =  | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                    | 0 0 1 |
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null) return null;
        int[][] res = new int[A.length][B[0].length];
        // Acol == Brow
        int Arow = A.length;
        int Acol = A[0].length;
        int Brow = B.length;
        int Bcol = B[0].length;
        for (int i = 0 ; i < Arow; i ++) {
            for (int k = 0 ; k < Acol; k ++) {
                if (A[i][k] != 0) {
                    for (int j = 0 ; j < Bcol; j ++) {
                        if (B[k][j] != 0) {
                            res[i][j] = res[i][j] + A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return res;
    }

}

