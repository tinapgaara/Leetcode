package google.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 9/20/15.
 */
public class NumIslands {

    // Calculate disconjunct forest and number of forests.
    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        int num = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                // Pay attention: DFS, the first loop need to use visitedFlags and judge it
                if ((grid[i][j] == '1')) {
                    DFS(i, j, grid);
                    num ++;
                }
            }
        }
        return num;
    }

    public void DFS(int x, int y, char[][] grid) {
        if ((x >= 0) && (x < grid.length) && (y >=0) && (y < grid[0].length)) {
            // Important !!! need to judge 1 here
            if ((grid[x][y] == '1')){ // grid's DFS is different format from graph's DFS
                // Important !!!!: set to be true before DFS others !!!!
                grid[x][y] = '2';

                DFS(x-1, y, grid);
                DFS(x, y-1, grid);
                DFS(x+1, y, grid);
                DFS(x, y+1, grid);

            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        grid[0] = new char[]{'1', '0', '0'};
        grid[1] = new char[]{'0', '0', '0'};
        grid[2] = new char[]{'0', '0', '1'};
        List<Integer> l = new ArrayList<>();
        NumIslands ob = new NumIslands();
        System.out.println(ob.numIslands(grid));
    }
}
