package facebook.topoSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 5/28/17.
 *
 * 210. Course Schedule II Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 59022
 Total Submissions: 218188
 Difficulty: Medium
 Contributor: LeetCode
 There are a total of n courses you have to take, labeled from 0 to n - 1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

 There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

 For example:

 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

 4, [[1,0],[2,0],[3,1],[3,2]]
 There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].


 Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.

 */
import java.util.*;
public class CourseScheduleII {

    public int[] findOrder_better(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        if (prerequisites == null) return res;
        int[] color = new int[numCourses];
        Arrays.fill(color, -1);
        Map<Integer, Set<Integer>> graph = buildgraph(numCourses, prerequisites, color);
        LinkedList<Integer> linkres = new LinkedList<>();
        // important !!! loop from number of courses instead of map, sometimes, preq could be empty, but we still have multiple nodes
        for (int i = 0 ; i < numCourses; i ++) {
            if(color[i] == 0) {
                if (! dfs(i, graph, color, linkres)) {
                    return new int[0];
                }
            }
        }
        int i = 0;
        for (Integer r : linkres) {
            res[i] = r;
            i ++;
        }
        return res;
    }
    public Map<Integer, Set<Integer>> buildgraph(int numCourses, int[][] prerequisites, int[] color) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0 ; i < numCourses; i ++) {
            graph.put(i, new HashSet<>());
            // important !!!!, need to set color[i] = 0 to show that we have this node
            color[i] = 0;
        }
        for(int[] preq : prerequisites) {
            int from = preq[0];
            int to = preq[1];
            graph.get(from).add(to);
        }
        return graph;
    }
    public boolean dfs(int node, Map<Integer, Set<Integer>> graph, int[] color, LinkedList<Integer> linkres) {
        color[node] = 1;
        if (graph.containsKey(node)) {
            Set<Integer> neighbors = graph.get(node);
            for (Integer neighbor  : neighbors) {
                if (color[neighbor] == 1) return false;
                else if (color[neighbor] == 0) {
                    if (! dfs(neighbor, graph, color, linkres)) return false;
                }
            }
        }
        linkres.add(node);
        color[node] = 2;
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) return null;
        if (prerequisites.length == 0) {
            int[] resEmpty = new int[numCourses];
            for (int i = 0 ; i < resEmpty.length; i ++) {
                resEmpty[i] = i;
            }
            return resEmpty;
        }

        List<Integer> res = new ArrayList<Integer>();
        boolean[][] adj = new boolean[numCourses][numCourses];
        int[] color = new int[numCourses];

        // build graph
        for (int i = 0 ; i < prerequisites.length; i ++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];

            if (! adj[start][end]) {
                adj[start][end] = true;
            }
        }

        // dfs
        for (int i = 0 ; i < numCourses; i ++) {
            if (color[i] == 0) {
                // Important !!!!!!
                if(! dfs(i, adj, color, res)) {
                    res = new ArrayList<Integer>();
                    break;
                }
            }
        }

        // combine independent code
        int[] resArr = new int[res.size()];
        for (int i = 0 ; i < res.size(); i ++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    public boolean dfs(int i, boolean[][] adj, int[] color, List<Integer> res) {
        color[i] = 1;
        for (int j = 0 ; j < color.length; j ++) {
            if (adj[i][j]) {
                // cycle
                if(color[j] == 1) return false;
                if (color[j] == 0) {
                    if(! dfs(j, adj, color, res) ) return false;
                }
            }
        }
        color[i] = 2;
        res.add(i);
        return true;
    }
}
