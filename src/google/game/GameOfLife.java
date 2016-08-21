package google.game;

/**
 * Created by yingtan on 11/11/15.
 */
/*

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
(taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

* rule:
* if cur == 1:
*   neighbors < 2 个 's value == 1 -> 0
*   neighbors >3 个's value == 1 -> 0
*   neighbors == 2 个 or == 3 个's value == 1: -> 1
*
* if cur == 0:
*   neighbors == 3个's value == 1: -> 1
*
*
*
*   Follow up:
Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite,
which would cause problems when the active area encroaches the border of the array. How would you address these problems?
* */
public class GameOfLife {


    // Solution 2: how to do this in place ??
    // use int[][] board: int can store two states using bits
    // 00: state not changed
    // 01: state changes from 0 -> 1
    // 10: staet cahnges from 1 -> 0
    // 11: state remains 1

    public void gameOfLife_Bits(int[][] board) {
        if ((board == null) || (board.length == 0)) return;

        int row = board.length;
        if (row == 0) return;

        int col = board[0].length;

        // initialize:  00  10
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col ; j ++) {
                if (board[i][j] == 1) {
                    board[i][j] = board[i][j] << 1;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int liveNum = findLivesNumsInNeighbors_Bits(i, j, board);

                int oldstatus = board[i][j] >> 1;
                if (oldstatus == 1) { // get old status
                    /*
                    if ((liveNum < 2) || (liveNum > 3)) { // 10 - > 10: keep same: nothing to do here

                    }
                    */
                    if ((liveNum == 2) || (liveNum == 3)) {  // 10 -> 11
                        board[i][j] = board[i][j] + 1;
                    }
                } else { // 0
                    if (liveNum == 3) {  // 00 -> 01
                        board[i][j] = board[i][j] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = board[i][j] & 1;
            }
        }
    }


    public int findLivesNumsInNeighbors_Bits(int x, int y, int[][] board) {
        int lives = 0;
        int[] delta = new int[]{-1,0,1};
        for (int i = 0 ; i < delta.length; i ++) {
            int newx = x + delta[i];
            for (int j = 0 ; j < delta.length; j ++) {
                if ((delta[i] == 0) && (delta[j] == 0)) continue;
                int newy = y + delta[j];
                if ((newx >= 0) && (newx < board.length) && (newy >= 0) && (newy < board[0].length)) {
                    // old status : (board[newx][newy] >> 1)
                    if ((board[newx][newy] >> 1) == 1) {
                        lives ++;
                    }
                }
            }
        }
        return lives;
    }


    // Solution 1: not in place
    public void gameOfLife(int[][] board) {
        if ((board == null) || (board.length == 0)) return;

        int row =  board.length;
        if (row == 0) return;

        int col = board[0].length;

        int[][] newboard = new int[row][col];
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col ; j ++) {
                int liveNum = findLivesNumsInNeighbors(i, j, board);

                if (board[i][j] == 1) {
                    if ((liveNum < 2) || (liveNum > 3)){
                        newboard[i][j] = 0;
                    }
                    else if ((liveNum == 2) || (liveNum == 3)) {

                        newboard[i][j] = 1;
                    }
                }
                else { // 0
                    if (liveNum == 3) {
                        newboard[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0 ; j < col; j ++) {
                board[i][j] = newboard[i][j];
            }
        }
    }

    public int findLivesNumsInNeighbors(int x, int y, int[][] board) {
        int lives = 0;
        int[] delta = new int[]{-1,0,1};
        for (int i = 0 ; i < delta.length; i ++) {
            int newx = x + delta[i];
            for (int j = 0 ; j < delta.length; j ++) {
                if ((delta[i] == 0) && (delta[j] == 0)) continue;
                int newy = y + delta[j];
                if ((newx >= 0) && (newx < board.length) && (newy >= 0) && (newy < board[0].length)) {
                    if (board[newx][newy] == 1) {
                        lives ++;
                    }
                }
            }
        }
        return lives;
    }

    public static void main(String[] args) {
        GameOfLife ob = new GameOfLife();
        int[][] nums = new int[][]{{0,1,0,0},{1,1,0,0},{0,1,1,1},{0,0,0,1}};
        ob.gameOfLife(nums);
    }
}
