package DFSBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import java.awt.Point;

/**
 * Created by yingtan on 9/20/15.
 */
public class NumIslands {

    // For consecutive '1' in grid, using DFS rather than BFS
    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        boolean[][] visitedFlags = new boolean[row][col];
        int num = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if ((grid[i][j] == '1') && (! visitedFlags[i][j])) {
                    DFS(grid, i, j, visitedFlags);
                    num ++;
                }
            }
        }
        return num;
    }

    public void DFS(char[][] grid, int x, int y, boolean[][] visitedFlags) {
        if ((x >=0) && (x < grid.length) && (y >= 0) && (y < grid[0].length)
                && (! visitedFlags[x][y])) {
            char ch = grid[x][y];

            if (ch == '1') {
                visitedFlags[x][y] = true;
                DFS(grid, x-1, y, visitedFlags);
                DFS(grid, x+1, y, visitedFlags);
                DFS(grid, x, y-1, visitedFlags);
                DFS(grid, x, y+1, visitedFlags);
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
