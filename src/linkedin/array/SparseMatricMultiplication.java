package linkedin.array;

/**
 * Created by yingtan on 11/29/17.
 */
public class SparseMatricMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null) return null;
        int rowA = A.length;
        int colA = A[0].length;
        int rowB = B.length;
        int colB = B[0].length;

        int[][] C = new int[rowA][colB];
        for (int i = 0 ; i < rowA; i ++) {
            for (int k = 0 ; k < colA; k ++) {
                if (A[i][k] != 0) {
                    for (int j = 0 ; j < colB; j ++) {
                        if (B[k][j] != 0) {
                            C[i][j] = C[i][j] + A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return C;
    }
}
