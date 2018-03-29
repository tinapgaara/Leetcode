package facebook.dfs;

/**
 * Created by yingtan on 5/20/17.
 *
 * 79. Word Search Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 121004
 Total Submissions: 462150
 Difficulty: Medium
 Contributor: LeetCode
 Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

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
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null) return false;

        int row = board.length;
        int col = board[0].length;

        boolean[][] vis = new boolean[row][col];

        for (int i = 0 ; i < row ; i ++) {
            for (int j = 0 ; j < col; j ++) {
                if(recurSearch(board, i, j, vis, word, 0)) return true;
            }
        }
        return false;
    }
    public boolean recurSearch(char[][] board, int i, int j, boolean[][] vis, String word, int index) {
        if (index >= word.length()) return true;

        if ( (i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length) ) {
            if (! vis[i][j]) {
                vis[i][j] = true;
                char ch = word.charAt(index);
                if (ch == board[i][j]) {
                    if( recurSearch(board, i+1, j, vis, word, index + 1) ||
                            recurSearch(board, i, j+1, vis, word, index + 1) ||
                            recurSearch(board, i-1, j, vis, word, index + 1) ||
                            recurSearch(board, i, j-1, vis, word, index + 1) )
                        return true;
                }
                vis[i][j] = false;
            }
        }
        return false;
    }

}
