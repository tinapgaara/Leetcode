package graph;

import java.util.*;

/**
 * Created by yingtan on 10/7/15.
 */
public class GraphValidTree {
    /*
    * Leetcode: Graph Valid Tree
    *
    *
    * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

    For example:

    Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

    Important:
    Any connected graph without simple cycles is a tree.

    Note: you can assume that no duplicate edges will appear in edges.
    Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
    // Need to add [0,1] and [1,0] at the same time:
    // Need to judge the visitedFlags using edges instead of node's color
    * */

    public boolean validTree(int n, int[][] edges) {

        Map<Integer, List<Integer>> nodes = new HashMap<>();
        int nodeNoMax = -1;
        if (edges == null) return false;
        if (edges.length == 0) {
            if (n == 1) return true;
            else return false;
        }

        HashSet<Integer> nodeSet = new HashSet<Integer>();
        for (int i = 0 ; i < edges.length ; i ++) {
            int source = edges[i][0];
            int end  = edges[i][1];

            nodeNoMax = Math.max(nodeNoMax, source); // for initialize the visitedEdges, and colors array
            nodeNoMax = Math.max(nodeNoMax, end);

            nodeSet.add(source); // for boundary cases
            nodeSet.add(end);
            if (nodes.containsKey(source)) {
                nodes.get(source).add(end);
            }
            else {
                List<Integer> neighbors = new ArrayList<>();
                neighbors.add(end);
                nodes.put(source, neighbors);
            }
            if (nodes.containsKey(end)) {
                nodes.get(end).add(source);
            }
            else {
                List<Integer> neighbors = new ArrayList<>();
                neighbors.add(source);
                nodes.put(end, neighbors);
            }
        }
        int[][] visitedEdges = new int[nodeNoMax+1][nodeNoMax+1];
        if (n !=  nodeSet.size()) { // for boundary cases
            nodeSet = null;
            return false;
        }
        int[] colors = new int[nodeNoMax+1]; // 0: WHITE  1:GREY 2: BLACK
        int startNode = edges[0][0];

        boolean flag = DFS(startNode, nodes, colors, visitedEdges);
        if (!flag ) return false;
        for (Map.Entry<Integer, List<Integer>> entry: nodes.entrySet()) {
            // Case 2: has more than one connected component, is not connected graph. This node is white, and
            if (colors[entry.getKey()] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean DFS(int node, Map<Integer, List<Integer>> graph, int[] colors,int[][] visitedEdges) {

        // Because has not judge the colors of nodes in loop, need to judge node's color here.
        // Case 1: If a new edge, visit it, and find a old grey node, then this is cycle
        if (colors[node] == 1) return false; //cycle: edge is not visited, but node is grey
        else if (colors[node] == 0) {
            colors[node] = 1; // Important !!! Need to make color to be grey here
            List<Integer> neighbors = graph.get(node);
            if (neighbors != null) { // when the node has no neighbors
                for (Integer neighbor : neighbors) {
                    // Important: !!! Judge the visitedEdge is 0 (not visit the edge), if so, visited.
                    if (visitedEdges[node][neighbor] == 0) {
                        visitedEdges[node][neighbor] = 1; // =1 for both sides of edges
                        visitedEdges[neighbor][node] = 1; // =1 for both sides of edges
                        if (!DFS(neighbor, graph, colors, visitedEdges)) return false;
                    }
                }
            }
        }
        colors[node] = 2;
        return true;
    }
    public static void main(String[] args) {

    }
}
