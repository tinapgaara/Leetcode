package google.DFS;

/**
 * Created by yingtan on 11/7/15.
 */
/*
*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

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
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null) return false;
        int row = board.length;
        if (row == 0) return false;
        int col = board[0].length;

        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if (DFS(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    public boolean DFS(char[][] board, int i, int j, String word, int index) {
        if (index > word.length() -1) return true;
        char ch = word.charAt(index);

        if ((i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length)) {
            if (ch == board[i][j]) {
                char tmp = ch;
                board[i][j] = '#';//mark as visited
                if (DFS(board, i+1, j, word, index+1)
                        ||DFS(board, i, j+1, word, index+1)
                        ||DFS(board, i-1, j, word, index+1)
                        ||DFS(board, i, j-1, word, index+1)) {
                    return true;
                }
                board[i][j] = tmp;//recover
            }
        }
        return false;
    }
}
