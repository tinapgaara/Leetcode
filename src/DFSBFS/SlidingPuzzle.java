package DFSBFS;

/**
 * Created by yingtan on 2/24/18.
 *
 * 773. Sliding Puzzle
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

 A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

 The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

 Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

 Examples:

 Input: board = [[1,2,3],[4,0,5]]
 Output: 1
 Explanation: Swap the 0 and the 5 in one move.
 Input: board = [[1,2,3],[5,4,0]]
 Output: -1
 Explanation: No number of moves will make the board solved.
 Input: board = [[4,1,2],[5,0,3]]
 Output: 5
 Explanation: 5 is the smallest number of moves that solves the board.
 An example path:
 After move 0: [[4,1,2],[5,0,3]]
 After move 1: [[4,1,2],[0,5,3]]
 After move 2: [[0,1,2],[4,5,3]]
 After move 3: [[1,0,2],[4,5,3]]
 After move 4: [[1,2,0],[4,5,3]]
 After move 5: [[1,2,3],[4,5,0]]
 Input: board = [[3,2,4],[1,5,0]]
 Output: 14

 */
import java.util.*;
public class SlidingPuzzle {
    // Question about backtrace + shortest distance
    // if you want to do similar to bfs but using dfs instead, consider dfs_topToDown, pass min[] and map<state, distance> to start point.
    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0) return 0;
        return dfs_solution(board);
    }
    public int dfs_solution(int[][] board) {
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        Map<String, Integer> minDist = new HashMap<>();
        for (int i = 0; i < 2; i ++) {
            for (int j = 0 ; j < 3; j ++) {
                if (board[i][j] == 0) {
                    dfs_topToDown(board, i, j, min, minDist, 0);
                    break;
                }
            }
        }
        if (min[0] == Integer.MAX_VALUE) {
            return -1;
        }
        else {
            return min[0];
        }
    }
    // show how to use dfs to do backtrace and also calculate minDistance as BFS ? !!!!
    // Important !!! can visit one cell multiple times, vis[i][j] does not work here, hence, the dfs_downToTop does not work because will dead loop.
    public void dfs_topToDown(int[][] board, int i, int j, int[] min, Map<String, Integer> minDist , int dist) {
        if (dist > min[0]) {
            return;
        }
        String key = encode(board);
        if (valid(key)) {
            minDist.put(key, 0);
            min[0] = Math.min(min[0], dist);
            return;
        }
        // important !!!
        if (minDist.containsKey(key) && minDist.get(key) < dist) {
             return;
        }
        // important !!! put to map before loop !!!!
        //  dfs_downToTop: put to map after loop.
        //  dfs_topToDown: put to map before loop.
        // store minDist to start point instead of minDist to end point.
        minDist.put(key, dist);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                // WRONG!!!! most important part !!! one cell can be visited multiple times here, swapped multiple times
                //if (! vis[newi][newj]) {
                    //swap
                    int tmp = board[newi][newj];
                    board[newi][newj] = board[i][j];
                    board[i][j] = tmp;
                    // do dfs
                    dfs_topToDown(board, newi, newj, min, minDist, dist + 1);
                    // back trace, revert here
                    int newtmp = board[newi][newj];
                    board[newi][newj] = tmp;
                    board[i][j] = newtmp;
                }
            //}
        }
        return;
    }

    public String encode(int[][] board) {
        String code =  "";
        for (int i = 0; i < 2; i ++) {
            for (int j = 0 ; j < 3; j ++) {
                code = code + (char)(board[i][j] + '0');
            }
        }
        return code;
    }
    public boolean valid(String code) {
        return code.equals("123450");
    }
    public static void main(String[] args) {
        SlidingPuzzle ob = new SlidingPuzzle();
        int[][] board = {{3,2,4}, {1,5,0}};
        System.out.println(ob.slidingPuzzle(board));
    }
}
