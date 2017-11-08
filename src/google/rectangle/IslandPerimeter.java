package google.rectangle;

/**
 * Created by yingtan on 8/15/17.
 *
 * 463. Island Perimeter
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 Example:
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (grid[i][j] == 0) continue;

                // left side
                if (j == 0 || grid[i][j-1] == 0) res ++;
                if (i == 0 || grid[i-1][j] == 0) res ++ ;
                if (j == col - 1 || grid[i][j+1] == 0) res ++;
                if (i == row - 1 || grid[i+1][j] == 0) res ++;
            }
        }
        return res;
    }
}
