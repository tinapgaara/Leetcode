package google.dp;

/**
 * Created by yingtan on 7/23/17.
 */
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if ( (grid == null) || (grid.length == 0)) return 0;
        int row = grid.length;
        int col = grid[0].length;

        int[][] numEnemies = new int[row][col];
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (grid[i][j] == 'E') {
                    updatekillEnemies(grid, numEnemies, row, col, i, j);
                }
            }
        }
        int max = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, numEnemies[i][j]);
                }
            }
        }
        return max;

    }

    public void updatekillEnemies(char[][] grid, int[][] numEnemies, int row, int col, int x, int y) {
        // cal this col up
        for (int i = x - 1; i >= 0 ; i --) {
            if (grid[i][y] == '0') {
                numEnemies[i][y] = numEnemies[i][y] + 1;
            }
            else if (grid[i][y] == 'W') break;
        }
        // cal this col down
        for (int i = x + 1; i < row ; i ++) {
            if (grid[i][y] == '0') {
                numEnemies[i][y] = numEnemies[i][y] + 1;
            }
            else if (grid[i][y] == 'W') break;
        }
        // cal this row left
        for (int j = y - 1; j >= 0 ; j --) {
            if (grid[x][j] == '0') {
                numEnemies[x][j] = numEnemies[x][j] + 1;
            }
            else if (grid[x][j] == 'W') break;
        }
        // cal this row right
        for (int j = y + 1; j < col ; j ++) {
            if (grid[x][j] == '0') {
                numEnemies[x][j] = numEnemies[x][j] + 1;
            }
            else if (grid[x][j] == 'W') break;
        }
    }
}
