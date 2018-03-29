package apple.trie;
import java.util.*;
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) return false;
        int[] color = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] preq : prerequisites) {
            int to = preq[0];
            int from = preq[1];
            if (! graph.containsKey(to)) {
                graph.put(to, new HashSet<>());
            }
            graph.get(to).add(from);
        }
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0 ; i < numCourses; i ++) {
            if (color[i] == 0) {
                // not vis
                if(! dfs(graph, i, color, res)) {
                    return false;
                }
            }
        }
        // check independent node ??
        return true;
    }
    public boolean dfs(Map<Integer, Set<Integer>> graph, int node, int[] color, LinkedList<Integer> res) {
        color[node] = 1; // visiting
        if (graph.containsKey(node)) {
            for (Integer neighbor : graph.get(node)) {
                if (color[neighbor] == 0) {
                    // not vos
                    if (! dfs(graph, neighbor, color, res)) {
                        return false;
                    }
                }
                else if (color[neighbor] == 1) {
                    // cycle
                    return false;
                }
            }
        }
        color[node] = 2; // finished

        return true;
    }
}
