package facebook.dfs;

/**
 * Created by yingtan on 3/20/18.
 *
 * 785. Is Graph Bipartite?
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an undirected graph, return true if and only if it is bipartite.

 Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

 The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

 Example 1:
 Input: [[1,3], [0,2], [1,3], [0,2]]
 Output: true
 Explanation:
 The graph looks like this:
 0----1
 |    |
 |    |
 3----2
 We can divide the vertices into two groups: {0, 2} and {1, 3}.
 Example 2:
 Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 Output: false
 Explanation:
 The graph looks like this:
 0----1
 | \  |
 |  \ |
 3----2
 We cannot find a way to divide the set of nodes into two independent subsets.
 */
import java.util.*;
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        return isBipartite_colors(graph);
    }
    // TODO: bfs solution
    public boolean isBipartite_colors(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        int[] color = new int[graph.length];
        Arrays.fill(color, -1); //not vist
        // blue: 0
        // red : 1
        // switch : 1 - orgColor
        for (int i = 0 ; i < color.length; i ++) {
            if (color[i] == -1) {
                color[i] = 0;
                if (! dfs(graph, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean dfs(int[][] graph, int node, int[] color) {
        for (Integer neighbor : graph[node]) {
            if (color[node] == color[neighbor]) {
                // not biparate
                return false;
            }
            else if (color[neighbor] == -1) {
                // no vist
                color[neighbor] = 1 - color[node]; // put in opposite set
                if (! dfs(graph, neighbor, color)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isBipartite_twosets(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        Map<Integer, Set<Integer>> graphMap = new HashMap<>();
        for(int i = 0 ; i < graph.length; i ++) {
            int[] edges = graph[i];
            int from = i;
            if(! graphMap.containsKey(from)) {
                graphMap.put(from, new HashSet<>());
            }
            for (int edge : edges) {
                graphMap.get(from).add(edge);
            }
        }
        for (int i = 0 ; i < graph.length; i ++) {
            if (! left.contains(i) && ! right.contains(i)) {
                left.add(i);
                if(! dfs_twosets(graphMap, i, left, right)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean dfs_twosets(Map<Integer, Set<Integer>> graphMap, int node, Set<Integer> left, Set<Integer> right) {
        if (graphMap.containsKey(node)) {
            for (Integer neighbor : graphMap.get(node)) {
                if (left.contains(node)) {
                    // check if neighbor is also contained in left
                    if (left.contains(neighbor)) return false;
                    else if (! right.contains(neighbor)) {
                        // never visited, put to right
                        right.add(neighbor);
                        if(! dfs_twosets(graphMap, node, left, right)) return false;
                    }
                }
                else if (right.contains(node)) {
                    // check if neighbor is also contained in right
                    if (right.contains(neighbor)) return false;
                    else if (! left.contains(neighbor)) {
                        // never visited, put to left
                        left.add(neighbor);
                        if (! dfs_twosets(graphMap, node, left, right)) return false;
                    }
                }
            }
        }
        return true;
    }
}
