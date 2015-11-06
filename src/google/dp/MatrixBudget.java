package google.dp;

/**
 * Created by yingtan on 11/4/15.
 */
public class MatrixBudget {

    public int maxSubMatrix(int[][] matrix, int budget) {

        int[][] prices = new int[matrix.length][matrix[0].length];
        int[][] areas  = new int[matrix.length][matrix[0].length];

        prices[0][0] = matrix[0][0];
        areas[0][0] = 1;
        int maxArea = 0;

        for (int i = 1; i < matrix.length; i ++) {
            if (prices[0][i] + prices[0][i-1] > budget) {
                maxArea = i;

                prices[0][i] = matrix[0][i];
                areas[0][i] = 1;
            }
            else {
                prices[0][i] = prices[0][i] + prices[0][i - 1];
                areas[0][i] = areas[0][i-1] + 1;
            }
        }

        for (int j = 1; j < matrix[0].length; j ++) {
            if (prices[j][0] + prices[j-1][0] > budget) {
                maxArea = j;

                prices[j][0] = matrix[j][0];
                areas[j][0] = 1;
            }
            else {
                prices[j][0] = prices[j][0] + prices[j - 1][0];
                areas[j][0] = areas[j-1][0] + 1;
            }
        }

        for (int i = 1; i < matrix.length; i ++) {
            for (int j = 1; j < matrix.length ; j ++) {

            }
        }
        // TODO:
        return 0;
    }
}
