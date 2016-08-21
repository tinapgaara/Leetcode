package google.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/27/15.
 */
/*
类似安卓手机九个点的锁屏，找所有可能的序列
follow up: 有限制长度怎么改
* */
public class GenerateSequenceLock {

    public List<List<Integer>> generateSequence(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[][] visitedFlags = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                DFS(res, grid, i, j, visitedFlags, path);
            }
        }
        return res;
    }

    public void DFS(List<List<Integer>> res, int[][] grid, int x, int y,
                    boolean[][] visitedFlags, List<Integer> path) {
        if ((x >= 0) && (x < grid.length) && (y >= 0) && (y < grid[0].length)) {
            if (! visitedFlags[x][y]) {
                visitedFlags[x][y] = true;
                path.add(grid[x][y]);
                res.add(new ArrayList<Integer>(path));
                DFS(res, grid, x + 1, y, visitedFlags, path);
                DFS(res, grid, x-1, y, visitedFlags, path);
                DFS(res, grid, x, y+1, visitedFlags, path);
                DFS(res, grid, x, y-1, visitedFlags, path);
                path.remove(path.size()-1);
                visitedFlags[x][y] = false;
            }
        }
    }

    // follow-up
    public List<List<Integer>> generateSequence(int[][] grid, int k) {

        int row = grid.length;
        int col = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[][] visitedFlags = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                DFSWithLength(res, grid, i, j, visitedFlags, path, k);
            }
        }
        return res;
    }


    public void DFSWithLength(List<List<Integer>> res, int[][] grid, int x, int y,
                              boolean[][] visitedFlags, List<Integer> path, int k) {

        if ((x >= 0) && (x < grid.length) && (y >= 0) && (y < grid[0].length)) {
            if (! visitedFlags[x][y]) {
                visitedFlags[x][y] = true;
                path.add(grid[x][y]);
                if (path.size() == k) { // pay attention. Should be put here instead of start of this function.
                    res.add(new ArrayList<Integer>(path));
                    path.remove(path.size()-1); // must remove
                    visitedFlags[x][y] = false; // must reset flag here
                    return;
                }
                DFSWithLength(res, grid, x + 1, y, visitedFlags, path, k);
                DFSWithLength(res, grid, x - 1, y, visitedFlags, path, k );
                DFSWithLength(res, grid, x, y + 1, visitedFlags, path, k);
                DFSWithLength(res, grid, x, y - 1, visitedFlags, path, k);
                path.remove(path.size()-1);
                visitedFlags[x][y] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2,3},{4,5,7},{8,9,10}};
        GenerateSequenceLock ob = new GenerateSequenceLock();
        System.out.println(ob.generateSequence(grid, 4));
    }
}
