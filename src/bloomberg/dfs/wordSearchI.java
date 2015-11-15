package bloomberg.dfs;

/**
 * Created by yingtan on 11/15/15.
 */
/*
*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent"
cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
* */
public class wordSearchI {

    public boolean exist(char[][] board, String word) {
        if (board == null) return false;
        int row = board.length;
        if (row == 0) return false;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (DFS(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    // has a way which does not need to define visitedFlags
    public boolean DFS(char[][] board, int x, int y, String word, int index) {
        if (index >=  word.length()) {
            return true;
        }
        if ((x >= 0) && (x < board.length) && (y >= 0) && (y < board[0].length)) {
            char ch = word.charAt(index);
            if (ch == board[x][y]) {
                if (board[x][y] != '#') {
                    board[x][y] = '#'; // mark as visited
                    boolean flag = DFS(board, x + 1, y, word, index + 1) |
                            DFS(board, x - 1, y, word, index + 1) |
                            DFS(board, x, y + 1, word, index + 1) |
                            DFS(board, x, y - 1, word, index + 1);

                    if (flag) return true; // must be return true before assign board[x][y] a new value: ch
                    board[x][y] = ch; // recover

                }
            }
        }
        return false;
    }
}
