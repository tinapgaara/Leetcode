package google.mianjing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/6/17.
 *
 * 两个人有relation是'E' or 'F', eg.  0 <-> 1 is 'E',
 * 1 <-> 2 is 'E', 2 <-> 3 is 'F',
 * 给一个input String chain eg. "EEF",
 * 一个int start, 一个int end, print 从start到end的一条path,
 * 使这条path的relation是chain里的string, in this case is "0123"
 *
 * 给一个九宫格电话+0键，给一个起始点，给一个国际象棋里的马，然后问n步马能跳出的数字组合

 */
public class PathChain {

    public List<List<Integer>> hoursePaths(int i, int j, int n) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] vis = new boolean[3][3];
        dfs(matrix, vis, i, j, path, res, n);
        return res;
    }

    private void dfs(int[][] matrix, boolean[][] vis, int i, int j,
                     List<Integer> path, List<List<Integer>> res, int step) {

        if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            if (! vis[i][j]) {
                vis[i][j] = true;
                path.add(matrix[i][j]);
                // important !!!1
                if (path.size() == step) {
                    res.add(new ArrayList<>(path));
                    path.remove(path.size() - 1);
                    vis[i][j] = false;
                    return;
                }
                dfs(matrix, vis, i+1, j, path, res, step);
                dfs(matrix, vis, i-1, j, path, res, step);
                dfs(matrix, vis, i, j+1, path, res, step);
                dfs(matrix, vis, i, j-1, path, res, step);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PathChain ob = new PathChain();
        System.out.println(ob.hoursePaths(0, 0, 4));
    }

}
