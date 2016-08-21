package google.DFS;

import java.awt.*;
import java.util.Random;

/**
 * Created by yingtan on 11/27/15.
 */
public class MazeGeneration {

    public int[][] generateMaze(int[][] board) {
        Point up = new Point(-1, 0);
        Point down = new Point(1, 0);
        Point left = new Point(0, -1);
        Point right = new Point(0, 1);

        int row = board.length;
        int col = board[0].length;

        Point[] dir = new Point[]{up, down, left, right};

        int randomStartX = generateRandomIndex(row);
        int randomStartY = generateRandomIndex(col);

        boolean[][] visitedFlags = new boolean[row][col];

        generateDFS(board, randomStartX, randomStartY, visitedFlags);
        return board;
    }

    public void generateDFS(int[][] board, int x, int y, boolean[][] visitedFlags) {
        if ((x >= 0) && (x < board.length) && (y >= 0) && (y < board[0].length)) {
            if (! visitedFlags[x][y] && (board[x][y] != 1)) {

                visitedFlags[x][y] = true;
                board[x][y] = 1; // 1 is maze , need to mark the middle point . how to mark ?

                // TODO: need to save the node which open to current node, and mark previous node
                generateDFS(board, x+1, y, visitedFlags);
                generateDFS(board, x, y+1, visitedFlags);
                generateDFS(board, x-1, y, visitedFlags);
                generateDFS(board, x, y-1, visitedFlags);
                    // TODO: how to judge it is end of generating maze ??? when there is not trace back ? how to deal in DFS?
                /*
                    if(allVisited(visitedFlags))
                        return true;
                        */

                    //board[x][y] = 0;
                    // visitedFlags[x][y] = false;
                }
            }
        }

    public int generateRandomIndex(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public boolean allVisited(boolean[][] visitedFlags) {
        for (int i = 0 ; i < visitedFlags.length; i ++) {
            for (int j = 0 ; j < visitedFlags[0].length ; j ++) {
                if (! visitedFlags[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = new int[3][3];
        MazeGeneration ob = new MazeGeneration();
        board = ob.generateMaze(board);

        for (int i = 0 ; i < board.length; i ++) {
            for (int j = 0 ; j < board[0].length ; j ++) {
                System.out.println(board[i][j]);
            }
        }
        System.out.println();
    }
}
