package uber.hashmap;

/**
 * Created by yingtan on 12/2/17.
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;

        // check each row
        for (int i = 0 ; i < row; i ++) {
            boolean[] vis = new boolean[10];
            for (int j = 0 ; j < col; j ++) {
                if (board[i][j] == '.') continue;
                if (vis[board[i][j] - '0']) {
                    return false;
                }
                else {
                    vis[board[i][j] - '0'] = true;
                }
            }
        }

        // check each col
        for (int i = 0 ; i < col; i ++) {
            boolean[] vis = new boolean[10];
            for (int j = 0 ; j < row; j ++) {
                if (board[j][i] == '.') continue;
                if (vis[board[j][i] - '0']) {
                    return false;
                }
                else {
                    vis[board[j][i] - '0'] = true;
                }
            }
        }

        // check each 3*3 matrix
        for (int block = 0 ; block < 9; block ++) {
            boolean[] vis = new boolean[10];
            // (i:0-2, j:0-2 , i: 1-3, j:0-2)
            for (int i = block/3 * 3; i < block/3 * 3 + 3; i ++) {
                //System.out.println(i);
                for (int j = (block%3)*3; j < (block%3) *3 + 3; j ++) {
                    if (board[i][j] == '.') continue;
                    if (vis[board[i][j] - '0']) {
                        return false;
                    }
                    else {
                        vis[board[i][j] - '0'] = true;
                    }
                }
            }
        }
        return true;

    }
}
