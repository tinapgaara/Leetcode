package google.DFS;

/**
 * Created by yingtan on 11/11/17.
 *
 * 一个九宫格电话+0键，给一个起始点，给一个国际象棋里的马，然后问n步马能跳出的数字组合
 * 逻辑这么写有没有问题 并且 这个可以用dp做吗 dp 怎么做???

 */
public class HoursePath {

    public int jumpWays(int[][] matrix, int steps, int startx, int starty) {
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        return dfs(matrix, startx, starty, steps, vis);
    }

    public int dfs(int[][] matrix, int i, int j, int len, boolean[][] vis) {
        if (len == 0) return 1;

        if (i >= 0 && i < matrix.length && j >=0 && j < matrix[0].length) {
            if (! vis[i][j]) {
                vis[i][j] = true;

                int count = 0;
                int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
                for(int[] dir : dirs) {
                    int newx = dir[0] + i;
                    int newy = dir[1] + j;
                    count = count + dfs(matrix, newx, newy, len - 1, vis);
                }
                vis[i][j] = false;
                return count;
            }
        }
        return 0;
    }
}
