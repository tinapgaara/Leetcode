package DFSBFS;

/**
 * Created by yingtan on 2/20/18.
 * 694. Number of Distinct Islands
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

 Example 1:
 11000
 11000
 00011
 00011
 Given the above grid map, return 1.
 Example 2:
 11011
 10000
 00001
 11011
 Given the above grid map, return 3.

 Notice that:
 11
 1
 and
 1
 11
 */
import java.util.*;
public class NumberOfDistanctIslands {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        StringBuilder b;
        Set<String> shapes = new HashSet<>();
        for (int i = 0 ; i < grid.length; i ++) {
            for (int j = 0 ; j < grid[0].length; j ++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 2;
                    b = new StringBuilder();
                    dfs(grid, i, j, b);
                    //System.out.println(i + " " + j + " " + b.toString());
                    if (! shapes.contains(b.toString())) {
                        shapes.add(b.toString());
                    }
                }
            }
        }
        return shapes.size();
    }
    public void dfs(int[][] grid, int i, int j, StringBuilder b) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        String[] strDir = {"U", "D", "L", "R"};
        for (int k = 0;  k < dirs.length; k ++) {
            int[] dir = dirs[k];
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length) {
                if (grid[newi][newj] == 1) {
                    grid[newi][newj] = 2; // mark as vis
                    b.append(strDir[k]);
                    dfs(grid, newi, newj, b);
                    // this is back, must do this, need to make sure dfs all the way down, can not back, when back, this is marked as "B", for case:
                    /*
                    * 1 1 0
                    * 0 1 1
                    * 0 0 0
                    * 1 1 1
                    * 0 1 0
                    * These are two different islands. If don't add "B", noth will be marked as  "RDR" , and be the same
                    * */
                    b.append("B");
                }
            }
        }
    }
}
