package vmware;

import java.util.List;
import java.util.*;

/**
 * Created by yingtan on 4/18/18.
 */
public class FindLenDPathInGraph {
    public List<List<Integer>> findPathsLengthD(Map<Integer, Set<Integer>> graph, int d) {
        Set<Integer> vis = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (Integer node : graph.keySet()) {
            path.add(node);
            vis.add(node);
            dfs(node, vis, path, res, graph, d);
            path.remove(path.size() - 1);
            vis.remove(node);
        }
        return res;
    }
    public void dfs(Integer node, Set<Integer> vis, List<Integer> path, List<List<Integer>> res, Map<Integer, Set<Integer>> graph, int d) {
        if (path.size() == d) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        if (graph.containsKey(node)) {
            for (Integer neighbor : graph.get(node)) {
                if (! vis.contains(neighbor)) {
                    vis.add(neighbor);
                    path.add(neighbor);
                    dfs(neighbor, vis, path, res, graph, d);
                    path.remove(path.size() - 1);
                    vis.remove(neighbor);
                }
            }
        }
    }
    public static void main(String[] args) {
        FindLenDPathInGraph ob = new FindLenDPathInGraph();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> node1s = new HashSet<>();
        node1s.add(2);
        node1s.add(3);
        node1s.add(4);
        graph.put(1, node1s);
        Set<Integer> node2s = new HashSet<>();
        node1s.add(3);
        node1s.add(4);
        node1s.add(5);
        graph.put(2, node2s);
        System.out.println(ob.findPathsLengthD(graph, 3));

    }
}
