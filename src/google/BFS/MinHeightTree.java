package google.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yingtan on 2/25/17.
 *
 * 310. Minimum Height Trees
 */
public class MinHeightTree {

    public class Node {
        List<Node> children;
        int color;
        int val;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<Node>();
            this.color = 0; // 0 : unvisited ,  1: not finished,  2: visited
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<Integer>();
        if ( (edges == null) || (edges.length == 0) ) {
            if (n == 1) res.add(0);
            return res;
        }
        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        for (int i = 0 ; i < n ; i ++) {
            Node newNode = new Node(i);
            nodes.put(i, newNode);
        }

        for (int i = 0 ; i < edges.length; i ++) {
            int start = edges[i][0];
            int end = edges[i][1];
            Node startNode = nodes.get(start);
            Node endNode = nodes.get(end);
            startNode.children.add(endNode);
            endNode.children.add(startNode);
        }

        List<Integer> leaves = new ArrayList<Integer>();
        for(Node node : nodes.values()) {
            if (node.children.size() == 1) {
                leaves.add(node.val);
            }
        }

        int curNum = n;
        while (curNum > 2) {
            List<Integer> newLeaves = new ArrayList<Integer>();
            for (Integer i: leaves) {
                Node node = nodes.get(i);
                for (Node child : node.children) {
                    child.children.remove(node);
                    if (child.children.size() == 1) {
                        newLeaves.add(child.val);
                    }
                }
            }

            curNum = curNum - leaves.size();
            leaves = newLeaves;
        }
        return leaves;
    }
}
