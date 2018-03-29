package BitOperation;

/**
 * Created by yingtan on 2/28/18.
 * 661. Image Smoother
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

 Example 1:
 Input:
 [[1,1,1],
 [1,0,1],
 [1,1,1]]
 Output:
 [[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
 Explanation:
 For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 */
public class ImageSmoother {
    // using game of life idea: newState | oldState -> bit
    public int[][] imageSmoother(int[][] M) {
        if (M == null || M.length == 0) return M;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        for (int i = 0 ; i < M.length; i ++) {
            for (int j = 0 ; j < M[0].length; j ++) {
                int sum = 0;
                int count = 0;
                for (int[] dir : dirs) {
                    int next_i = i + dir[0];
                    int next_j = j + dir[1];
                    if (next_i >= 0 && next_i < M.length && next_j >= 0 && next_j < M[0].length) {
                        count ++;
                        sum = sum + (M[next_i][next_j] & 1);
                    }
                }
                int val = (sum + M[i][j]) / count;
                if (val == 1) {
                    M[i][j] = (1 << 1) + M[i][j];
                }
            }
        }
        for (int i = 0 ; i < M.length; i ++) {
            for (int j = 0 ; j < M[0].length; j ++) {
                M[i][j] = M[i][j] & 1;
            }
        }
        return M;
    }
}
