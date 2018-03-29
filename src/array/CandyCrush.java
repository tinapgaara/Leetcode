package array;

/**
 * Created by yingtan on 2/26/18.
 *
 * 723. Candy Crush
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 This question is about implementing a basic elimination algorithm for Candy Crush.

 Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

 If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 You need to perform the above rules until the board becomes stable, then return the current board.


 */
public class CandyCrush {
    public int[][] candyCrush(int[][] board) {
        if (board == null || board.length == 0) return board;
        return crush(board);
    }
    public int[][] crush(int[][] board) {
        while (true) {
            boolean solvable = solve(board);
            if (! solvable) break;
        }
        return board;
    }
    public boolean solve(int[][] board) {
        if (! markSolveOnes(board)) {
            return false;
        }
        for (int j = 0; j < board[0].length; j ++) {
            int nonCrush = board.length - 1;
            for (int i = board.length - 1; i >= 0; i --) {
                if (board[i][j] > 0) { // no crush
                    board[nonCrush][j] = board[i][j];
                    nonCrush --;
                }// else, crush
            }
            while (nonCrush >= 0) {
                board[nonCrush][j] = 0;
                nonCrush --;
            }
        }
        return true;
    }
    public boolean markSolveOnes(int[][] board) {
        boolean solve = false;
        for (int i = 0 ; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] == 0) continue;
                // check each cell righter side, and down sider
                // righter
                // Using Math.abs is IMPORTANT !!!!!! : solve
                /*
                2
                2
                2 2 2
                this case
                */
                int val = Math.abs(board[i][j]);
                // check if have consecutive +3 same cells horizontal
                if (j + 2 < board[0].length && val == Math.abs(board[i][j+1]) && val == Math.abs(board[i][j+2])) {
                    // at least 3 consecutive cells
                    int tmp = j;
                    solve = true;
                    while(tmp < board[0].length && Math.abs(board[i][tmp]) == val) {
                        board[i][tmp] = -1 * val; // set to negative as consecutive ones
                        tmp ++;
                    }
                }
                // check if have consecutive +3 same cells vertical
                if (i + 2 < board.length && val == Math.abs(board[i+1][j]) && val == Math.abs((board[i+2][j]))) {
                    int tmp = i;
                    solve = true;
                    while(tmp < board.length && Math.abs(board[tmp][j]) == val) {
                        board[tmp][j] = -1 * val; // set to negative as consecutive ones
                        tmp ++;
                    }
                }
            }
        }
        return solve;
    }
}
