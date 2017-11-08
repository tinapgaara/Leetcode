package google.unionfind;

import java.util.*;

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

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        List<Integer> result = new ArrayList<>();
        if (positions == null || n <= 0 || m <= 0) {
            return result;
        }
        boolean[][] visitedFlags = new boolean[m][n];
        int count = 0;
        // each island index -> its clustered island number, index starting from 0
        int[] roots = new int[m * n];

        for (int i = 0 ; i < positions.length; i ++) {
            int x = positions[i][0];
            int y = positions[i][1];

            if (! visitedFlags[x][y]) {
                visitedFlags[x][y] = true;

                count ++;
                int curClusterId = getId(x, y, n);
                // create new cluster
                roots[curClusterId] = curClusterId;

                for (int j = 0 ; j < 4; j ++) {
                    int neighborX = x + DIRS[j][0];
                    int neighborY = y + DIRS[j][1];

                    if ((neighborX >= 0) && (neighborX < m) && (neighborY >= 0) && (neighborY < n)
                            && (visitedFlags[neighborX][neighborY])) {
                        int neighborId = getId(neighborX, neighborY, n);

                        int neighborRoot = findRoot(neighborId, roots);
                        if (neighborRoot != curClusterId) {
                            // union neighbor with id's cluster
                            roots[curClusterId] = neighborRoot;
                            // update the current cluster's id equals to neighborRoot, and use this again in the next loop
                            curClusterId = neighborRoot;
                            count --;
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

    private int findRoot(int id, int[] roots) {
        while (id != roots[id]) {
            // important
            /*
            1 0 1
            0 0 0
            0 0 0

            ->

            1 1 1
            0 0 0
            0 0 0

            0  2  0 -1 -1 -1 -1 -1 -1

            then, when change to
            1 1 1
            0 1 0
            0 0 0

            0  2  0 -1 -1 -1 -1 -1 -1 will change to

            0  0  0 -1  0 -1 -1 -1 -1

            */
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }

    private Map<Integer, Integer> rootMap = new HashMap<>();

    public List<Integer> numIslands2_MAp(int m, int n, int[][] positions) {

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
                // add new island
                rootMap.put(id, id);

                for (int j = 0 ; j < 4; j ++) {
                    int neighborX = x + DIRS[j][0];
                    int neighborY = y + DIRS[j][1];

                    if ((neighborX >= 0) && (neighborX < m) && (neighborY >= 0) && (neighborY < n)
                            && (visitedFlags[neighborX][neighborY])) {
                        int neighborId = getId(neighborX, neighborY, n);
                        int neighborRoot = findRoot_map(neighborId);
                        //int root = findRoot(id);
                        if (neighborRoot != id) {
                            rootMap.put(id, neighborRoot);
                            count --;
                            id = neighborRoot; // current tree root = joined tree root
                        }
                    }
                }
            }
            result.add(count);
        }

        return result;
    }


    private int findRoot_map(int id) {
        while (id != rootMap.get(id)) {
            id = rootMap.get(id);

        }
        return id;
    }

    public static void main(String[] args) {
        NumIslandII ob = new NumIslandII();
        int[][] positions = new int[][]{{0,0},{0,2},{0, 1},{1,1}};
        System.out.println(ob.numIslands2(3,3,positions));
    }
}
