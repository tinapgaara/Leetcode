package google.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 11/10/15.
 */
/*
* Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
* write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
Show More Hint
Note: you can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

How to judge cycle in undirected Graph ?

Use DFS(curNodeNo, parentNo):
    judge: if (neighborNo == parentNo): if so, has cycle
*
* */
public class GraphValidTree {


    public boolean validTree(int n, int[][] edges) {

        if (edges == null) return false;
        if ((n == 1) && (edges.length == 0))  return true;
        else if ((n > 1) && (edges.length == 0))  return false;

        int[] colors = new int[n];
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0 ; i < n ; i ++) {
            adj.put(i, new ArrayList<>());
        }

        for (int i = 0 ; i < edges.length ; i ++) {
            int startNodeNo = edges[i][0];
            int endNodeNo = edges[i][1];
            adj.get(startNodeNo).add(endNodeNo);
            adj.get(endNodeNo).add(startNodeNo);
        }

        if(!DFS(0, adj, colors, -1)) { // judge cycle
            return false;
        }

        for (int i = 0 ; i < n ; i ++) {
            if (colors[i] != 2) return false;
        }
        return true;
    }

    public boolean DFS(int nodeNo, Map<Integer, List<Integer>> adj, int[] colors, int parent) { // Very Important !!!! judge cycle in undirected graph
        colors[nodeNo] = 1;
        List<Integer> neighbors = adj.get(nodeNo);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (colors[neighbor] == 0) {
                    boolean isValid = DFS(neighbor, adj, colors, nodeNo);
                    if (!isValid) return false;
                }
                else if (colors[neighbor] == 1) {
                    if (parent == nodeNo) return false;
                }
                else return false;
            }
        }
        colors[nodeNo] = 2;
        return true;
    }

    public static void main(String[] args) {
        GraphValidTree tree = new GraphValidTree();
        int[][] edges = new int[][]{{0,3},{3,2}}; //{0, 1}, {0, 2}, {0, 3}, {1, 4}
        System.out.println(tree.validTree(4, edges));
    }
}
