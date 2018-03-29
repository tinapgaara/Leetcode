package graph;

/**
 * Created by yingtan on 1/18/18.
 */
import java.util.*;
public class SearchAMaze {

    // only find one path
    public List<int[]> findOnePath_dfs(int[][] maze, int si, int sj, int ei, int ej) {
        List<int[]> res = new ArrayList<>();
        // 0 : white, 1: wall
        dfs(maze, si, sj, ei, ej, res);
        return res;
    }
    public boolean dfs(int[][] maze, int i, int j, int ei, int ej, List<int[]> res) {
        if (i == ei && j == ej) {
            res.add(new int[]{i, j});
            return true;
        }
        if (i >= 0 && i < maze.length && j >= 0 && j < maze[0].length) {
            if (maze[i][j] == 0) {
                maze[i][j] = 2; // already visited
                // can go
                res.add(new int[]{i, j});
                if (dfs(maze, i+1, j, ei, ej, res) ||
                    dfs(maze, i-1, j, ei, ej, res) ||
                    dfs(maze, i, j+1, ei, ej, res) ||
                    dfs(maze, i, j-1, ei, ej, res)) {
                    return true;// can reach
                }
                res.remove(res.size() - 1);
                // this point can not reach target anyway, no need to set back to color 0.
            }
        }
        return false;
    }
    public List<int[]> findOnePath_bfs_shortestPath(int[][] maze, int si, int sj, int ei, int ej) {
        List<int[]> res = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{si, sj});
        res.add(new int[]{si, sj});
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            if (x == ei && y == ej) {
                res.add(new int[]{x, y});// find one shortest path
                return res;
            }
            if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length) {
                if (maze[x][y] == 0) {
                    maze[x][y] = 2;
                    res.add(new int[]{x, y});
                    queue.offer(new int[]{x+1, y});
                    queue.offer(new int[]{x-1, y});
                    queue.offer(new int[]{x, y+1});
                    queue.offer(new int[]{x, y-1});
                }
            }
        }
        return res;
    }

    public List<List<int[]>> findAllPaths_dfs(int[][] maze, int si, int sj, int ei, int ej) {
        List<List<int[]>> res = new ArrayList<>();
        List<int[]> path = new ArrayList<>();
        // 0 : white, 1: wall
        dfs(maze, si, sj, ei, ej, path, res);
        return res;
    }
    public boolean dfs(int[][] maze, int i, int j, int ei, int ej, List<int[]> path, List<List<int[]>> res) {
        if (i == ei && j == ej) {
            path.add(new int[]{i, j});
            res.add(new ArrayList<>(path));
            return true;
        }
        if (i >= 0 && i < maze.length && j >= 0 && j < maze[0].length) {
            if (maze[i][j] == 0) {
                maze[i][j] = 2; // already visited
                // can go
                path.add(new int[]{i, j});
                boolean reachable =  (dfs(maze, i+1, j, ei, ej, path, res) ||
                        dfs(maze, i-1, j, ei, ej, path, res) ||
                        dfs(maze, i, j+1, ei, ej, path, res) ||
                        dfs(maze, i, j-1, ei, ej, path, res));
                path.remove(path.size() - 1);
                // since this point may be good one, could be re-visited future for all paths, set back to white here.
                maze[i][j] = 0;
                return reachable;
            }
        }
        return false;
    }
    public void findAllPaths_bfs_shortestPath(int[][] maze, int si, int sj, int ei, int ej) {
        // this is more complicated one.
        // Step 1. Using BFS, label each node with its distance from the start node. Stop when you get to the end node. This graph already shows shortest dist from s to e
        // Step 2: Using DFS, find all paths from the end node to the start node such that the depth strictly decreases for each step of the path.
    }
}
