package graph;

import java.util.*;

/**
 * Created by yingtan on 9/27/15.
 */
public class CourseScheduleII {

    // Example for DFS to record color, time
    public static int time = 0;
    public class Graph {
        Map<Integer, List<Node>> map;
        int nodeNum;

        public Graph(Map<Integer, List<Node>> map, int num) {
            this.map = map;
            nodeNum = num;
        }
    }

    public class Node {
        int nodeNo;
        int startTime;
        int finishTime;

        public Node(int nodeNo, int startTime, int finishTime) {
            this.nodeNo = nodeNo;
            this.startTime = startTime;
            this.finishTime = finishTime;
        }
        public Node(int nodeNo) {
            this.nodeNo = nodeNo;
            startTime = 0;
            finishTime = 0;
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> order = new ArrayList<Integer>();

        if ( (prerequisites == null) || (prerequisites.length == 0)) {
            int[] courses = new int[numCourses];
            for (int i = 0 ; i < numCourses; i ++)
                courses[i] = i;
            return courses;
        }
        Graph g = buildGraph(prerequisites);
        int[] visitedFlags = new int[numCourses];
        for (int i = 0 ; i < numCourses; i ++) {
            if (visitedFlags[i] == 0) {
                boolean flag = DFS(g.map, visitedFlags, new Node(i), order);
                if (! flag) {
                    int[] empty = new int[0];
                    return empty;
                }
            }
        }
        int[] res = new int[order.size()];
        for (int i = 0; i < order.size(); i ++ )
            res[i] = order.get(i).intValue();

        return res;
    }

    public boolean DFS(Map<Integer, List<Node>> map, int[] visitedFlags, Node cur,
                       List<Integer> order) {
        time++;
        cur.startTime = time;
        visitedFlags[cur.nodeNo] = 1;
        List<Node> neighbors = map.get(cur.nodeNo);
        System.out.println(cur.nodeNo);
        if ((neighbors != null) && (neighbors.size() > 0) ){
            for (Node neighbor: neighbors) {
                if (visitedFlags[neighbor.nodeNo] == 0) {
                    if (! DFS(map, visitedFlags, neighbor, order)) return false;
                }
                else if (visitedFlags[neighbor.nodeNo] == 1){
                    visitedFlags[neighbor.nodeNo] = 2;
                    return false;
                }
            }
        }
        visitedFlags[cur.nodeNo] = 2;
        time ++;
        cur.finishTime = time;
        System.out.println(cur.nodeNo + "," + time);
        order.add(0, cur.nodeNo);
        return true;
    }

    public Graph buildGraph(int[][] prerequisites) {
        int row = prerequisites.length;
        int maxNum = 0;
        Map<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
        for (int i = 0 ; i < row ; i ++) {
            int node_2 = prerequisites[i][0];
            int node_1 = prerequisites[i][1];
            maxNum = Math.max(maxNum, node_2);
            maxNum = Math.max(maxNum, node_1);
            Node newNode = new Node(node_2);
            if (map.containsKey(node_1)) {
                map.get(node_1).add(newNode);
            }
            else {
                List<Node> neighbors = new ArrayList<Node>();
                neighbors.add(newNode);
                map.put(node_1, neighbors);
            }
        }
        Graph g = new Graph(map, (maxNum+1));
        return g;
    }

    public static int[] findOrder_2(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int[] preCnt = new int[numCourses];
        List<Integer>[] nextSet = new List[numCourses];
        for(int i=0; i<numCourses; i++)
            nextSet[i] = new ArrayList<>();

        for(int[] p : prerequisites) {
            preCnt[p[0]]++;
            nextSet[p[1]].add(p[0]);
        }

        Queue<Integer> sourceNode = new LinkedList<Integer>();
        for(int i=0; i<numCourses; i++)
            if(preCnt[i] == 0)
                sourceNode.add(i);

        for(int i=0; i<numCourses; i++) {
            if(sourceNode.isEmpty())
                return new int[0];
            int n = sourceNode.poll();
            result[i] = n;
            for(int next : nextSet[n]) {
                preCnt[next]--;
                if(preCnt[next] == 0)
                    sourceNode.add(next);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CourseScheduleII ob = new CourseScheduleII();
        int[][] p = new int[][]{{0,1}};
        int[] a = ob.findOrder(2, p);
        for (int i = 0 ; i < a.length; i ++)
            System.out.println(a[i]);
    }
}
