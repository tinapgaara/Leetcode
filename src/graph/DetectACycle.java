package graph;

/**
 * Created by yingtan on 1/18/18.
 */
import java.util.*;
public class DetectACycle {
    int[] colors;
    public boolean directGraphHasCycle(Map<Integer, Set<Integer>> graph) {
        for (Integer node : graph.keySet()) {
            if (colors[node] == 0) {
                if (dfsCycle_directGraph(node, graph, colors)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfsCycle_directGraph(Integer node, Map<Integer, Set<Integer>> graph, int[] colors) {
        colors[node] = 1;
        for (Integer neighbor: graph.get(node)) {
            if (colors[neighbor] == 0) {
                if (dfsCycle_directGraph(neighbor, graph, colors)) return true;
            }
            else if (colors[neighbor] == 1) {
                return true;
            }
        }
        colors[node] = 2;
        return false;
    }

    public boolean undirectGraphHasCycle(Map<Integer, Set<Integer>> graph) {
        // only use vis flag is enough
        boolean[] vis = new boolean[100];
        for (Integer node : graph.keySet()) {
            if (! vis[node]) {
                if (dfsCycle_undirectGraph(node, graph, node, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfsCycle_undirectGraph(Integer node, Map<Integer, Set<Integer>> graph, int parent, boolean[] vis) {
        for (Integer neighbor: graph.get(node)) {
            if (! vis[neighbor]) {
                if (dfsCycle_undirectGraph(neighbor, graph, node, vis)) return true;
            }
            else if (neighbor != parent) { // a ->b
                return true;
            }
        }
        colors[node] = 2;
        return false;
    }
}
