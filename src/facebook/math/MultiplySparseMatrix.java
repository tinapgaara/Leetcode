package facebook.math;

/**
 * Created by yingtan on 5/14/17.
 */
public class MultiplySparseMatrix {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 0,0}, {-1, 0, 3}};
        int[][] B = new int[][]{{7,0,0,0}, {0,0,0,0},{0,0,1,0}};
        MultiplySparseMatrix ob = new MultiplySparseMatrix();
        ob.multiply(A, B);
    }
}
