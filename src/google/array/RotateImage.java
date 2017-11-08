package google.array;

/**
 * Created by yingtan on 5/6/17.
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        if ( (matrix == null) || (matrix.length == 0) ) return;

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0 ; i < row ; i ++) {
            for (int j = 0 ; j < col - i; j ++) { // col - i : important !
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[row - 1 - j][col - 1 - i];
                matrix[row - 1 - j][col - 1 - i] = tmp; // important !!!! matrix[i][j] = matrix[n -1 - j][ n -1 -i]
            }
        }

        for (int i = 0 ; i < row / 2; i ++) { // row / 2: important
            for (int j = 0 ; j < col; j ++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[row - 1 - i][j];
                matrix[row - 1 - i][j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        RotateImage im = new RotateImage();
        im.rotate(matrix);
    }
}
