package bloomberg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by max2 on 10/15/15.
 */
/*
* Leetcode: Unique Paths II
*
*  Follow up for "Unique Paths":

    Now consider if some obstacles are added to the grids. How many unique paths would there be?

    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    For example,
    There is one obstacle in the middle of a 3x3 grid as illustrated below.

    [
      [0,0,0],
      [0,1,0],
      [0,0,0]
    ]
    The total number of unique paths is 2.
* */
public class UniquePathII {

    public class Node {
        private Node prev;
        private int x;
        private int y;

        public Node (int x, int y) {
            this.x = x;
            this.y = y;
            prev = null;
        }

        public Node (int x, int y, Node prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) return 0;

        int row = obstacleGrid.length;
        if (row == 0) return 0;

        int col = obstacleGrid[0].length;
        int[][] uniqueNumber = new int[row][col];

        if (obstacleGrid[0][0] == 0) uniqueNumber[0][0] = 1;
        else uniqueNumber[0][0] = 0;
        for (int i = 1 ; i < row; i ++) {
            if (obstacleGrid[i][0] == 1) uniqueNumber[i][0] = 0;
            else uniqueNumber[i][0] = uniqueNumber[i-1][0];
        }
        for (int i = 1 ; i < col; i ++) {
            if (obstacleGrid[0][i] == 1) uniqueNumber[0][i] = 0;
            else uniqueNumber[0][i] = uniqueNumber[0][i-1];
        }
        for (int i = 1; i < obstacleGrid.length; i ++) {
            for (int j = 1; j < obstacleGrid[0].length ; j ++) {
                if (obstacleGrid[i][j] == 1) uniqueNumber[i][j] = 0;
                else uniqueNumber[i][j] = uniqueNumber[i-1][j] + uniqueNumber[i][j-1];
            }
        }
        return uniqueNumber[row-1][col-1];
    }

    public List<List<Integer>> findPathsWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (obstacleGrid == null) return res;

        int row = obstacleGrid.length;
        if (row == 0) return res;

        int col = obstacleGrid[0].length;


        Node start = new Node(0, 0);
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(start);
        int[][] visitedFlags = new int[row][col];

        while (!queue.isEmpty()) {
            Node prevNode = queue.poll();
            int x = prevNode.x;
            int y = prevNode.y;
            if ((x == row-1) && (y == col-1)) {
                constructPath(res, prevNode, obstacleGrid);
            }
            if ((x >= 0) && (x < row) && (y >= 0) && (y < col)) {
                if (visitedFlags[x][y] == 0) {
                    visitedFlags[x][y] = 1;
                    if (obstacleGrid[x][y] == 0) {
                        queue.offer(new Node(x-1, y,prevNode));
                        queue.offer(new Node(x, y-1, prevNode));
                        queue.offer(new Node(x+1, y, prevNode));
                        queue.offer(new Node(x, y+1, prevNode));
                    }
                }
            }
        }
        return res;
    }

    public void constructPath(List<List<Integer>> list, Node curNode, int[][] obstacleGrid) {
        List<Integer> path = new ArrayList<Integer>();
        while (curNode != null) {
            path.add(0, obstacleGrid[curNode.x][curNode.y]);
            curNode = curNode.prev;
        }
        list.add(path);
    }

    public static void main(String[] args) {
        UniquePathII ob = new UniquePathII();
        int[][] obstacles = new int[][]{{0,1,1},{0,0,1},{1,0,0},{1,1,0}};
        ob.findPathsWithObstacles(obstacles);
    }
}
