package DFSBFS;

/**
 * Created by yingtan on 2/25/18.
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

    public static void main(String[] args) {
        NetworkDelayTime ob = new NetworkDelayTime();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int[][] times2 = {{3, 5, 78}, {2, 1, 1}, {1, 3, 0}, {4, 3, 59}, {5, 3, 85}, {5, 2, 22}, {2, 4, 23}, {1, 4, 43}, {4, 5, 75}, {5, 1, 15}, {1, 5, 91}, {4, 1, 16}, {3, 2, 98}, {3, 4, 22},
                {5, 4, 31}, {1, 2, 0}, {2, 5, 4}, {4, 2, 51}, {3, 1, 36}, {2, 3, 59}};
        System.out.println(ob.networkDelayTime(times2, 5, 5));
    }
}
