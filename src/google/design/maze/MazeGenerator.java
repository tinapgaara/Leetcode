package google.design.maze;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by yingtan on 11/28/15.
 */
public class MazeGenerator {

    private final int mSizeX;
    private final int mSizeY;
    private final int[][] mMaze;

    public MazeGenerator(int sizeX, int sizeY) {
        mSizeX = sizeX;
        mSizeY = sizeY;
        mMaze = new int[mSizeX][mSizeY];
        generateMaze(0, 0);
    }

    private void generateMaze(int curX, int curY) {
        DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (DIR dir : dirs) {
            int nextX = curX + dir.dx;
            int nextY = curY + dir.dy;
            if (between(nextX, mSizeX) && between(nextY, mSizeY) && (mMaze[nextX][nextY] == 0)) {
                mMaze[curX][curY] |= dir.bit;
                mMaze[nextX][nextY] |= dir.opposite.bit;
                generateMaze(nextX, nextY);
            }
        }
    }

    public void display() {
        for (int i = 0; i < mSizeY; i++) {
            // draw the north edge
            for (int j = 0; j < mSizeX; j++) {
                System.out.print((mMaze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < mSizeX; j++) {
                System.out.print((mMaze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < mSizeX; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }

    private enum DIR {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIR opposite;

        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }

        private DIR(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    };

    public static void main(String[] args) {
        int x = 3;
        int y = 4;
        MazeGenerator maze = new MazeGenerator(y, x);
        maze.display();
    }

}
