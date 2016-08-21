package google.dp;

/**
 * Created by yingtan on 12/19/15.
 */
public class RangeSumMatrix {

    private int[][] dp;

    public RangeSumMatrix(int[][] matrix) {
        if ((matrix == null) || (matrix.length == 0)) return;

        dp = new int[matrix.length][matrix[0].length];
        int row = matrix.length;
        int col = matrix[0].length;
        dp[0][0] = matrix[0][0];

        for (int i = 1 ; i < row; i ++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }
        for (int j = 1; j < col; j ++) {
            dp[0][j] = dp[0][j-1] + matrix[0][j];
        }
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
            }
        }

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if ((dp == null) || (dp.length == 0)) return 0;
        if ((row1 > 0) && (col1 > 0))
            return (dp[row2][col2] - (dp[row1-1][col2] + dp[row2][col1-1] - dp[row1-1][col1-1]));
        else if (row1 > 0) {
            return dp[row2][col2] - dp[row1-1][col2];
        }
        else if (col1  > 0) {
            return dp[row2][col2] - dp[row2][col1-1];
        }
        else
            return dp[row2][col2];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{8,-4,5},{-1,4,4},{-2,3,1},{-4,4,3}};
        RangeSumMatrix ob = new RangeSumMatrix(matrix);
        System.out.println(ob.sumRegion(0,1,0,2));
    }
}
