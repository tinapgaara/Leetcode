package graph;

/**
 * Created by yingtan on 1/18/18.
 *
 *
 * 130. Surrounded Regions
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 For example,
 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X
 */
import java.util.*;
public class SuroundedRegion {

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        for (int i = 0 ; i < board.length; i ++) {
            if (board[i][0] == 'O') {
                mark(board, i, 0);
            }
        }
        for (int i = 0 ; i < board.length; i ++) {
            if (board[i][board[0].length - 1] == 'O') {
                mark(board, i, board[0].length - 1);
            }
        }
        for (int j = 0 ; j < board[0].length; j ++) {
            if (board[0][j] == 'O') {
                mark(board, 0, j);
            }
        }
        for (int j = 0 ; j < board[0].length; j ++) {
            if (board[board.length - 1][j] == 'O') {
                mark(board, board.length - 1, j);
            }
        }
        for (int i = 0 ; i < board.length; i ++) {
            for (int j = 0 ; j < board[0].length; j ++) {
                if (board[i][j] != 'B') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    public void mark(char[][] board, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if (board[x][y] == 'O') {
                    board[x][y] = 'B';
                    queue.offer(new int[]{x+1, y});
                    queue.offer(new int[]{x-1, y});
                    queue.offer(new int[]{x, y+1});
                    queue.offer(new int[]{x, y-1});
                }
            }
        }
    }

    public static void main(String[] args) {
        SuroundedRegion ob = new SuroundedRegion();
        char[][] matrix = {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        ob.solve(matrix);
    }
}
