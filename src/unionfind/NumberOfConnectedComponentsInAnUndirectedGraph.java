package unionfind;

/**
 * Created by yingtan on 2/25/18.
 *
 * 323. Number of Connected Components in an Undirected Graph
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

 Example 1:
 0          3
 |          |
 1 --- 2    4
 Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

 Example 2:
 0           4
 |           |
 1 --- 2 --- 3
 Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.


 */
import java.util.*;
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) return n;
        int[] parents = new int[n];
        for (int i = 0 ; i < n; i ++) {
            parents[i] = i;
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int fromParent = findParent(parents, from);
            int toParent = findParent(parents, to);
            if (fromParent != toParent) {
                parents[toParent] = fromParent;
            }
        }
        Set<Integer> groups = new HashSet<>();
        for (int i = 0 ; i < parents.length; i ++) {
            int parent = findParent(parents, i);
            groups.add(parent);
        }
        return groups.size();
    }
    public int findParent(int[] parents, int node) {
        if (parents[node] != node) {
            parents[node] = findParent(parents, parents[node]);
        }
        return parents[node];
    }
}
