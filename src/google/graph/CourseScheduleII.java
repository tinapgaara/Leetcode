package google.graph;

import java.util.*;

/**
 * Created by yingtan on 11/22/15.
 */
/*
*其实类似 leetcode的 course scheduleII 用拓扑排序做 但是course是用string表示，而且输入中可能会包括单个
course的出现，就是输入可能是{{"abc", "bcd"}, {"dbe"}, {"cdd", "avd"},......} 稍微处理一下就行了google
* */
public class CourseScheduleII {

    private Map<Integer, List<Integer>> edges = new HashMap<>();
    private int curNum = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] order = new int[numCourses];

        if ((prerequisites == null) || (prerequisites.length == 0)) {
            int[] courses = new int[numCourses];
            for (int i = 0; i < numCourses; i++)
                courses[i] = i;
            return courses;
        }

        buildGraph(prerequisites);

        int[] visitedFlags = new int[numCourses];
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            int nodeNo = entry.getKey();
            if (visitedFlags[nodeNo] == 0) {
                if (!DFS(nodeNo, visitedFlags, order)) {
                    int[] empty = new int[0];
                    return empty;
                }
            }
        }

        // Important !!!!
        // for independent node, need to use visitedFlags to judge if visited, if not, must be independent
        if (curNum < numCourses) {
            for (int i = 0 ; i < visitedFlags.length; i ++) {
                if (visitedFlags[i] == 0) {
                    order[curNum] = i;
                    curNum ++;
                }
            }
        }
        return order;
    }

    public boolean DFS(int nodeNo, int[] visitedFlags, int[] order) {
        visitedFlags[nodeNo] = 1;
        if (edges.get(nodeNo) != null) {
            for (Integer neighbor : edges.get(nodeNo)) {
                if (visitedFlags[neighbor] == 0) {
                    if (! DFS(neighbor, visitedFlags, order)) return false;
                }
                else if (visitedFlags[neighbor] == 1) {
                    visitedFlags[neighbor] = 2;
                    return false;
                }
            }
        }
        visitedFlags[nodeNo] = 2;
        order[curNum] = nodeNo;
        curNum ++;
        return true;
    }

    public void buildGraph(int[][] prerequisites) {

        for (int i = 0 ; i < prerequisites.length ; i ++) {
            int preCourse = prerequisites[i][1];
            int curCourse = prerequisites[i][0];

            if (edges.containsKey(curCourse)) {
                edges.get(curCourse).add(preCourse);
            }
            else {
                List<Integer> neighbors = new ArrayList<>();
                neighbors.add(preCourse);
                edges.put(curCourse, neighbors);
            }
        }
    }

    public static void main(String[] args) {
        CourseScheduleII ob = new CourseScheduleII();
        int[][] p = new int[][]{{3,0},{0,1}};
        ob.findOrder(4, p);
    }
}
