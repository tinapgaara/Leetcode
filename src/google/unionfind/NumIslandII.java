package google.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/22/15.
 */
/*
*
* Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
* */
public class NumIslandII {

    private Map<Integer, Integer> rootMap = new HashMap<>();
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        List<Integer> result = new ArrayList<>();
        if (positions == null || n <= 0 || m <= 0) {
            return result;
        }
        boolean[][] visitedFlags = new boolean[m][n];
        int count = 0;

        for (int i = 0 ; i < positions.length; i ++) {
            int x = positions[i][0];
            int y = positions[i][1];

            if (! visitedFlags[x][y]) {
                visitedFlags[x][y] = true;

                count ++;
                int id = getId(x, y, n); // why not x * m +  y ????
                rootMap.put(id, id);

                for (int j = 0 ; j < 4; j ++) {
                    int neighborX = x + DIRS[j][0];
                    int neighborY = y + DIRS[j][1];

                    if ((neighborX >= 0) && (neighborX < m) && (neighborY >= 0) && (neighborY < n)
                            && (visitedFlags[neighborX][neighborY])) {
                        int neighborId = getId(neighborX, neighborY, n);
                        int neighborRoot = findRoot(neighborId);
                        int root = findRoot(id);
                        if (neighborRoot !=  root) {
                            count --;
                            rootMap.put(root, neighborRoot);
                        }
                    }
                }
            }
            result.add(count);
        }

        return result;
    }

    private int getId(int x, int y, int n) {
        return x * n + y;
    }

    private int findRoot(int id) {
        int root = rootMap.get(id);
        while (root != rootMap.get(root)) {
            root = rootMap.get(root);
        }
        return root;
    }

    public static void main(String[] args) {
        NumIslandII ob = new NumIslandII();
        int[][] positions = new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        System.out.println(ob.numIslands2(3,3,positions));
    }
}
