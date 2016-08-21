package bloomberg.Impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 10/26/15.
 */
public class Maze {

    public class Node {
        private Node prev;
        private int x;
        private int y;
        private int value;

        public Node (int x, int y, int value) {
            this.x = x;
            this.y = y;
            prev = null;
            this.value = value;
        }

        public Node (int x, int y, Node prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }
    }

    // Solution 1: use matrix to show nodes: use more space
    Node[][] nodes;
    int row;
    int col;

    // Solution 2: use graph to keep record all 0 (possible entries) entries, ignore all 1. save space.
    // Node { int x, int y, List<Node> neighbors }
    // Maze { HashSet<Node> nodes }

    public Maze(int row, int col) {
        nodes = new Node[row][col];
        this.row = row;
        this.col = col;
    }

    // walk from start point to end point, return all paths
    public List<List<Integer>> findPathsWithObstacles(Maze maze, int startx, int starty, int endx, int endy) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (maze == null) return res;

        int row = maze.row;
        if (row == 0) return res;

        int col = maze.col;

        if ( (maze.nodes == null) || (startx < 0) || (startx >= row) || (endx < 0) || (endy >= col) ) return res;

        Node start = maze.nodes[startx][starty];
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(start);
        int[][] visitedFlags = new int[row][col];

        while (!queue.isEmpty()) {
            Node prevNode = queue.poll();
            int x = prevNode.x;
            int y = prevNode.y;
            if ((x == row-1) && (y == col-1)) {
                constructPath(res, prevNode, maze);
            }
            if ((x >= 0) && (x < row) && (y >= 0) && (y < col)) {
                if (visitedFlags[x][y] == 0) {
                    visitedFlags[x][y] = 1;
                    if ( maze.nodes[x][y].value == 0) {
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

    public void constructPath(List<List<Integer>> list, Node curNode, Maze maze) {
        List<Integer> path = new ArrayList<Integer>();
        while (curNode != null) {
            path.add(0, maze.nodes[curNode.x][curNode.y].value);
            curNode = curNode.prev;
        }
        list.add(path);
    }
}
