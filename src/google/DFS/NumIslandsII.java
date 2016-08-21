package google.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/21/15.
 */
public class NumIslandsII {

    // time out of limit
    // time complexity ?? of DFS and BFS ????
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<Integer>();
        if ((positions == null) || (positions.length == 0)) {
            return res;
        }
        int row = positions.length;
        int[][] islands = new int[m][n];
        int firstX = positions[0][0];
        int firstY = positions[0][1];
        islands[firstX][firstY] = 1;
        int num = 1;
        res.add(num);

        for (int i = 1; i < row; i ++) {
            int x  = positions[i][0];
            int y = positions[i][1];
            islands[x][y] = 1;
            if (connect(islands, positions, i, x, y)) {
                res.add(num);
            }
            else {
                num ++;
                res.add(num);
            }
        }
        return res;
    }

    public boolean connect(int[][] islands, int[][] positions, int beforeRow, int x, int y) {
        boolean[][] visitedFlags = new boolean[islands.length][islands[0].length];
        for (int i = 0 ; i < beforeRow; i ++) {
            int startX = positions[i][0];
            int startY = positions[i][1];
            if(DFS(visitedFlags, startX, startY, x, y, islands)) {
                return true;
            }
        }
        return false;
    }

    public boolean DFS(boolean[][] visitedFlags, int x, int y,
                       int connectedX, int connectedY, int[][] islands) {
        if ((x >= 0) && (x < islands.length) && (y >= 0) && (y < islands[0].length)) {
            if ((!visitedFlags[x][y]) && (islands[x][y] == 1)) {
                visitedFlags[x][y] = true;
                if ((x == connectedX) && (y == connectedY)) {
                    return true;
                }
                else {
                    return (DFS(visitedFlags, x+1, y, connectedX, connectedY, islands) ||
                            DFS(visitedFlags, x-1, y, connectedX, connectedY, islands) ||
                            DFS(visitedFlags, x, y+1, connectedX, connectedY, islands) ||
                            DFS(visitedFlags, x, y-1, connectedX, connectedY, islands));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NumIslandsII ob = new NumIslandsII();
        int[][] positions = new int[][]{{0,0},{0,1},{1,2},{2,1}};
        System.out.println(ob.numIslands2(3,3,positions));
    }
}
