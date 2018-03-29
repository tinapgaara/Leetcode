package facebook.graph;

/**
 * Created by yingtan on 5/29/17.
 *
 * 261. Graph Valid Tree Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 34500
 Total Submissions: 92431
 Difficulty: Medium
 Contributor: LeetCode
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

 For example:

 Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

 Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false
 */
import java.util.*;
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        // 1. no cycle
        // 2. can reach all nodes from one node : bfs
        if (edges == null) return false;
        // 1. undirected graph check cycle: #node - 1 == #edge
        if (edges.length != n - 1) {
            return false;
        }
        Map<Integer, Set<Integer>> graph = buildgraph(n, edges);
        // 2. check if can reach each node from one node
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        while(! queue.isEmpty()) {
            int node = queue.poll();
            if (graph.containsKey(node)) {
                Set<Integer> neighbors = graph.get(node);
                for (Integer neighbor : neighbors) {
                    if (! visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
        return visited.size() == n;
    }
    public Map<Integer, Set<Integer>> buildgraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0 ; i < n ; i ++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        return graph;
    }

    public boolean validTree_dfs(int n, int[][] edges) {
        if ( (edges == null) || (edges.length == 0) ) {
            if (n == 1) return true;
            else return false;
        }
        boolean[][] adj = new boolean[n][n];
        int[] color = new int[n];
        for (int i = 0 ; i < edges.length; i ++) {
            int start = edges[i][0];
            int end = edges[i][1];
            adj[start][end] = true;
            adj[end][start] = true;
        }

        int components = 0;

        for (int i = 0 ; i < n; i ++) {
            // important!!!
            if (color[i] == 0) {
                if(dfs(i, adj, color, -1)) {
                    components ++;
                }
                else {
                    return false;
                }
            }
        }

        if (components == 1) return true;
        else return false;
    }

    public boolean dfs(int i, boolean[][] adj, int[] color, int parent) {
        color[i] = 1;
        for (int j = 0 ; j < color.length; j ++) {
            if (adj[i][j]) {
                if (color[j] == 1) {
                    //Important !!! undirected graph judge cycle
                    if(parent == i) return false;
                }
                else if (color[j] == 0) {
                    if(!dfs(j, adj, color, i)) return false;
                }
                else //  Important !!!!
                    return false;
            }
        }
        color[i] = 2;
        return true;
    }
}
