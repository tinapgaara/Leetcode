package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 9/27/15.
 */
public class CourseSchedule {

    public class Graph {
        Map<Integer, List<Integer>> map;
        int nodeNum;

        public Graph(Map<Integer, List<Integer>> map, int num) {
            this.map = map;
            nodeNum = num;
        }
    }
    // check circle , DFS. if DFS meet grey nodes, then exist circle
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if ( (prerequisites == null) || (prerequisites.length == 0) ) return true;

        Graph g = buildGraph(prerequisites);
        Map<Integer, List<Integer>> map = g.map;
        int[] visitedFlags = new int[g.nodeNum];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int nodeNo = entry.getKey();
            boolean flag = DFS(nodeNo, visitedFlags, map);
            if ( ! flag) return false;
        }
        return true;
    }

    public boolean DFS(int nodeNo, int[] visitedFlags, Map<Integer, List<Integer>> map) {
        List<Integer> neighbors = map.get(nodeNo);
        visitedFlags[nodeNo] = 1;
        boolean res = true;
        if ( (neighbors == null) || (neighbors.size() == 0) ) {
            visitedFlags[nodeNo] = 2;
            return res;
        }

        for (int i = 0 ; i < neighbors.size(); i ++) {
            int anotherNodeNo = neighbors.get(i);
            if (visitedFlags[anotherNodeNo] == 0) {
                res = DFS(anotherNodeNo, visitedFlags, map);
                if (!res) return false;
            } else if (visitedFlags[anotherNodeNo] == 1) {// judge if cycle
                return false;
            }
        }
        visitedFlags[nodeNo] = 2;
        return res;
    }

    public Graph buildGraph(int[][] prerequisites) {
        int row = prerequisites.length;
        int maxNum = 0;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0 ; i < row ; i ++) {
            int node_2 = prerequisites[i][0];
            int node_1 = prerequisites[i][1];
            maxNum = Math.max(maxNum, node_2);
            maxNum = Math.max(maxNum, node_1);

            if (map.containsKey(node_1)) {
                map.get(node_1).add(node_2);
            }
            else {
                List<Integer> neighbors = new ArrayList<Integer>();
                neighbors.add(node_2);
                map.put(node_1, neighbors);
            }
        }
        Graph g = new Graph(map, (maxNum+1));
        return g;
    }

    public static void main(String[] args) {
        CourseSchedule ob = new CourseSchedule();
        int[][] p = new int[][]{{0,2},{1,2},{2,0}};
        System.out.println(ob.canFinish(3, p));
    }
}
