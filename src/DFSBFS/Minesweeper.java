package DFSBFS;

/**
 * Created by yingtan on 2/22/18.
 * 529. Minesweeper
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Let's play the minesweeper game (Wikipedia, online game)!

 You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

 Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

 If a mine ('M') is revealed, then the game is over - change it to 'X'.
 If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 Return the board when no more squares will be revealed.
 Example 1:
 Input:

 [['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

 Click : [3,0]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Explanation:

 Example 2:
 Input:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Click : [1,2]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Explanation:


 */
import java.util.*;
public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        int i = click[0];
        int j = click[1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            i = p[0];
            j = p[1];
            if (board[i][j] == 'M') {
                // case 1: If a mine ('M') is revealed, then the game is over - change it to 'X'.
                board[i][j] = 'X';
                return board;
            }
            if (board[i][j] == 'E') {
                int mines = neighborMines(board, i, j);
                // case 2: If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
                if (mines == 0) {
                    // no adj mine
                    board[i][j] = 'B';
                    for (int[] dir : dirs) {
                        int newi = i + dir[0];
                        int newj = j + dir[1];
                        if (inRange(newi, newj, board.length, board[0].length)) {
                            queue.offer(new int[]{newi, newj});
                        }
                    }
                }
                // case 3: If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
                else {
                    board[i][j] = (char)(mines + '0');
                }
            }
        }
        return board;
    }
    public int neighborMines(char[][] board, int i, int j) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        int count = 0;
        for (int[] dir : dirs) {
            if (inRange(i + dir[0], j + dir[1], board.length, board[0].length)) {
                if (board[i + dir[0]][j + dir[1]] == 'M') {
                    count ++;
                }
            }
        }
        return count;
    }
    public boolean inRange(int i, int j, int row, int col) {
        return (i >= 0 && i < row && j >= 0 && j < col);
    }
}
