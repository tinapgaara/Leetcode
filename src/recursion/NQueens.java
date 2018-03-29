package recursion;

/**
 * Created by yingtan on 1/11/18.
 *
 * 51. N-Queens
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 For example,
 There exist two distinct solutions to the 4-queens puzzle:

 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 */
import java.util.*;
public class NQueens {
    // return all cols ids as increasing rows
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0 ; i < n; i ++) {
            for (int j = 0 ; j < n; j ++) {
                board[i][j] = '.';
            }
        }
        // starts from the first row
        recurSolve(0, n, board, res);
        return res;
    }
    public void recurSolve(int rowId, int n, char[][] board,  List<List<String>> res) {
        // base case
        if (rowId == n) {
            res.add(construct(board));
            return;
        }
        for (int colId = 0; colId < n; colId++) {
            if (isValid(board, rowId, colId)) {
                board[rowId][colId] = 'Q';
                recurSolve(rowId + 1, n, board,res);
                board[rowId][colId] = '.';
            }
        }
    }
    public boolean isValid(char[][] board, int curRowId, int curColId) {
        // if cols collisions for previous rows
        for (int i = 0; i < curRowId; i++) {
            for (int j = 0 ; j < board.length; j ++) {
                if (board[i][j] == 'Q') {
                    int diff = Math.abs(curColId - j);
                    if ((diff == 0) ||  // same col
                            (diff == curRowId - i)) { // on the diagonal
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0 ; i < board.length; i ++) {
            String str = "";
            for (int j = 0 ; j < board[0].length; j ++) {
                str = str + board[i][j];
            }
            res.add(str);
        }
        return res;
    }
}
