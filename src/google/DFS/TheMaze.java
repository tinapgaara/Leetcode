package google.DFS;

import java.util.Arrays;

/**
 * Created by yingtan on 2/15/17.
 */
public class TheMaze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if ( (maze == null) || (maze.length == 0) || (start == null) || (start.length == 0) || (destination == null) || (destination.length == 0) )
            return false;

        int row = maze.length;
        int col = maze[0].length;
        boolean[][] vis = new boolean[row][col];
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        return dfs(start, destination, vis, maze, dirs);
    }

    public boolean dfs(int[] cur, int[] des, boolean[][] vis, int[][] maze, int[][] dirs) {
        if (vis[cur[0]][cur[1]]) return false;

        // reach destination
        if (Arrays.equals(cur, des)) return true;

        vis[cur[0]][cur[1]] = true;
        for (int[] dir : dirs) {
            int x = cur[0];
            int y = cur[1];

            // important part ! keep rolling until hit walls
            // when rolling, no need to judge if this cell has been visited, only entry point of dfs need to be judge if visited
            while(! reachWall(x+dir[0], y+dir[1], maze.length, maze[0].length) && (maze[x+dir[0]][y+dir[1]] != 1) ) {
                x = x + dir[0];
                y = y + dir[1];
            }

            // can stop here
            // then judge if reach dest
            if (dfs(new int[]{x,y}, des, vis, maze, dirs)) return true;
        }

        return false;
    }

    private boolean reachWall(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }

    public static void main(String[] args) {
        int[][] maze = new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},
                {0,0,0,0,0}};

        int[] start = new int[]{0, 4};
        int[] des = new int[]{1, 2};
        TheMaze ob  = new TheMaze();
        ob.hasPath(maze, start, des);
    }
}
