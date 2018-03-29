package graph;

/**
 * Created by yingtan on 1/18/18.
 *
 * 90. The Maze
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1
 */
public class TheMaze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // only require check if can reach, no need to use BFS, DFS is enough
        if (maze == null || maze.length == 0) return false;
        return dfs(maze, start[0],start[1], destination);
    }
    public boolean dfs(int[][] maze, int x, int y, int[] dest) {
        if (x == dest[0] && y == dest[1]) {
            return true;
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // choose the next stop point to start
        for (int[] dir : dirs) {
            int newx = x + dir[0];
            int newy = y + dir[1];

            while(! reachsBoundary(maze, newx, newy) && maze[newx][newy] != 1) {
                newx = newx + dir[0];
                newy = newy + dir[1];
            }
            // step back
            newx = newx - dir[0];
            newy = newy - dir[1];
            // should be in boundary
            if (maze[newx][newy] == 0) {
                maze[newx][newy] = 2;
                if (dfs(maze, newx, newy, dest)) {
                    return true;
                }
                // else, this pos can not lead to dest, so no need to set back to white.
            }
        }
        return false;
    }
    public boolean reachsBoundary(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) return true;
        else return false;
    }
}
