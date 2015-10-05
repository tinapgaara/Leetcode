package DFSBFS;

import java.awt.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 9/26/15.
 */
public class SurroundedRegion {

    public class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // starts from 0 which is on boundary, and find connected component which contains it
    // for the connected component, does not paint it.
    public void solve(char[][] board) {
        if (board == null) {
            return;
        }
        int row = board.length;
        if (row == 0) return;
        int col = board[0].length;
        boolean[][] visitedFlags = new boolean[row][col];
        int[][] ifPaint = new int[row][col]; // 0:paint 1: notPaint

        Queue<Point> queue = new LinkedList<Point>();
        int num = 0;
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if ( (board[i][j] == 'O')
                        && ((i == 0) || ( i == row - 1) || ( j == 0) || (j == col - 1)) ) {
                    queue.offer(new Point(i, j));
                    BFS(board, queue, visitedFlags, ifPaint);
                }
            }
        }
        for (int i = 0 ; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if ((board[i][j] == 'O') && (ifPaint[i][j] == 0)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void BFS(char[][] board, Queue<Point> queue, boolean[][] visitedFlags, int[][] ifPaint) {
        while( ! queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;

            if ((x >= 0) && (x < board.length) && (y >= 0) && (y < board[0].length)) {
                ifPaint[x][y] = 1;
                if ((! visitedFlags[x][y]) && (board[x][y] == 'O')) {
                    visitedFlags[x][y] = true;
                    queue.offer(new Point(x - 1, y));
                    queue.offer(new Point (x+1, y));
                    queue.offer(new Point (x, y-1));
                    queue.offer(new Point (x, y+1));
                }
            }
        }
    }

    public static void main(String[] args) {
        java.awt.Point p = new java.awt.Point(2,3);
        HashMap<java.awt.Point, Integer> h = new HashMap<>();


        h.put(p, 10);
        if (h.containsKey(new java.awt.Point(2,3)))
            System.out.println("hello");
        System.out.println(h.get(new java.awt.Point(2,3)));
        SurroundedRegion ob = new SurroundedRegion();
        char[][] board = new char[][]{{'O','O','O','O','X','X'},
                                      {'O','O','O','O','O','O'},{'O','X','O','X','O','O'},{'O','X','O','O','X','O'},
                {'O','X','O','X','O','O'},{'O','X','O','O','O','O'}};
        ob.solve(board);
    }
}
