package DFSBFS;

/**
 * Created by yingtan on 1/28/18.
 */
import java.util.*;
public class Dijkstra {
    public class NodeComparator implements Comparator<Integer> {
        Map<Integer, Integer> time;
        public NodeComparator(Map<Integer, Integer> time) {
            this.time = time;
        }
        public int compare(Integer i1, Integer i2) {
            return time.get(i1) - time.get(i2);
        }
    }
    // find shortest path between start and each every nodes
    public int shortestPath(int N, int startnode, int[][] edges, int endnode) {
        Map<Integer, Integer> minTime = new HashMap<>();
        for (int i = 0 ; i < N; i ++) {
            minTime.put(i, Integer.MAX_VALUE);
        }
        NodeComparator cmp = new NodeComparator(minTime);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(cmp);
        queue.offer(startnode);

       while(! queue.isEmpty()) {
            int node = queue.poll();
            int dist = minTime.get(node);
            for (int neighbor = 0 ; neighbor < N; neighbor ++) {
               // Important !!! can not use vis flag, can vis a node multiple times with a shorter distance
               //if (! vis[neighbor]) {
               int edgeLen = edges[node][neighbor];
               if (edgeLen != Integer.MAX_VALUE) {
                   // has edge
                   if (dist + edgeLen < minTime.get(neighbor)) {
                       minTime.put(neighbor, dist + edgeLen);
                       queue.offer(neighbor);
                   }
               }
           }
        }
        return minTime.get(endnode);
    }
}
