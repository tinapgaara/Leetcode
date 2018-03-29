package uber.graph;

import java.util.*;

/**
 * Created by yingtan on 10/29/17.
 */
public class GraphValidTree {

    // BFs version
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        // Important !!!! judge circle ....
        if (edges.length != n - 1) {
            return false;
        }
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        // check if can reach all nodes from node 0
        queue.offer(0);
        hash.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor)) {
                    continue;
                }
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }
        return (hash.size() == n);
    }

    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }

    public static void main(String[] args) {
        GraphValidTree ob = new GraphValidTree();
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(ob.validTree(5, edges));
    }
}
