package graph;

/**
 * Created by yingtan on 3/18/18.
 *
 * 743. Network Delay Time
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 There are N network nodes, labelled 1 to N.

 Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

 Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 Note:
 N will be in the range [1, 100].
 K will be in the range [1, N].
 The length of times will be in the range [1, 6000].
 All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

 */
import java.util.*;
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        // if (w,u) > (w,v) + (v,u), then, update (w, u)
        Map<Integer, Integer> minTime = new HashMap<>();
        for (int i = 0 ; i < N; i ++) {
            minTime.put(i, Integer.MAX_VALUE);
        }
        NodeComparator cmp = new NodeComparator(minTime);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(cmp);
        queue.offer(K - 1);
        minTime.put(K - 1, 0);
        //boolean[] vis = new boolean[N];
        int[][] delays = new int[N][N];
        for (int[] delay : delays) {
            Arrays.fill(delay, Integer.MAX_VALUE);
        }
        for (int[] time: times) {
            int source = time[0];
            int des = time[1];
            int dist = time[2];

            int sourceId = source - 1;
            int desId = des - 1;
            delays[sourceId][desId] = dist;
        }
        while(! queue.isEmpty()) {
            Integer curnode = queue.poll();
            // Important !!! can not use vis flag, can vis a node multiple times with a shorter distance
            //vis[curnode] = true;
            int dist = minTime.get(curnode);
            for (int neighbor = 0 ; neighbor < N; neighbor ++) {
                // Important !!! can not use vis flag, can vis a node multiple times with a shorter distance
                //if (! vis[neighbor]) {
                int edgeLen = delays[curnode][neighbor];
                if (edgeLen != Integer.MAX_VALUE) {
                    // has edge
                    if (dist + edgeLen < minTime.get(neighbor)) {
                        minTime.put(neighbor, dist + edgeLen);
                        queue.offer(neighbor);
                    }
                }
                //}
            }
        }
        int maxDelay = 0;
        for (Integer node : minTime.keySet()) {
            maxDelay = Math.max(maxDelay, minTime.get(node));
        }
        if (maxDelay == Integer.MAX_VALUE) return -1;
        return maxDelay;
    }
    public class NodeComparator implements Comparator<Integer> {
        Map<Integer, Integer> time;
        public NodeComparator(Map<Integer, Integer> time) {
            this.time = time;
        }
        public int compare(Integer i1, Integer i2) {
            return time.get(i1) - time.get(i2);
        }
    }
}
