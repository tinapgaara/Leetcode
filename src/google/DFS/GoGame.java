package google.DFS;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* 实现一个围棋盘里是否有 deadend的method(就是如果白子可以把黑子围住或者黑子可以把白子围住就return true),dfs, 做
完被指出一个BUG改
*
* */
public class GoGame {

    // 1 -> white
    // 2 -> black
    // 0 -> no item
    public boolean isDeadEnd(char[][] board) {

        // 1. judge if there is boundary, starts from boundary
        // TODO: time complexity ???
        int row = board.length;
        int col = board[0].length;

        boolean[][] visitedFlags = new boolean[row][col];
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (isBoundaryCell(board, i, j, visitedFlags)) {
                    int color = board[i][j];
                    DFS(board, visitedFlags, i, j, color); // time complexity ???
                }
            }
        }

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if ((board[i][j] != 0) && (!visitedFlags[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void DFS(char[][] board, boolean[][] visitedFlags, int x, int y, int color) {
        if ((x >= 0) && (x < board.length) && (y >= 0) && (y < board[0].length)) {
            if (! visitedFlags[x][y]) {
                if (board[x][y] == color) {
                    visitedFlags[x][y] = true;
                    DFS(board, visitedFlags, x+1, y, color);
                    DFS(board, visitedFlags, x, y+1, color);
                    DFS(board, visitedFlags, x-1, y, color);
                    DFS(board, visitedFlags, x, y-1, color);
                }
            }
        }
    }

    public boolean isBoundaryCell(char[][] board, int x, int y, boolean[][] visitedFlags) {

        if ((board[x][y] != 0) && (!visitedFlags[x][y])) {
            if (x - 1 >= 0) {
                if (board[x-1][y] == 0) {
                    return true;
                }
            }
            if (y - 1 >= 0) {
                if (board[x][y-1] == 0) {
                    return true;
                }
            }
            if (x + 1 < board.length) {
                if (board[x+1][y] == 0) {
                    return true;
                }
            }
            if (y + 1 < board[0].length) {
                if (board[x][y+1] == 0) {
                    return true;
                }
            }
            if ((x == 0) || (x == board.length-1) || (y == 0) || (y == board[0].length -1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GoGame ob = new GoGame();
        char[][] board = new char[][]{{0,2,2,0,0},{0,2,2,2,0},{0,1,2,1,1},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(ob.isDeadEnd(board));
    }
}
