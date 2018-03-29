package DFSBFS;

/**
 * Created by yingtan on 2/20/18.
 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) return image;
        if (image[sr][sc] == newColor) return image;
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    public void dfs(int[][] grid, int i, int j, int color, int newColor) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
            if (grid[i][j] == color) {
                // satisfy constraints and not vis before
                grid[i][j] = newColor;
                dfs(grid, i + 1, j, color, newColor);
                dfs(grid, i - 1, j, color, newColor);
                dfs(grid, i , j + 1, color, newColor);
                dfs(grid, i, j - 1, color, newColor);
            }
        }
    }
}
