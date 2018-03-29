package graph;

/**
 * Created by yingtan on 3/6/18.
 *
 * 787. Cheapest Flights Within K Stops
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

 Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

 Example 1:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 1
 Output: 200
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 Example 2:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500
 Explanation:
 The graph looks like this:


 The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 Note:

 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.

 */
import java.util.*;
public class CheapestFlightWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //Dijkstra
        // find shortest weights path from src - > dest
        if (flights == null || flights.length == 0) return 0;
        Map<Integer, Integer> minprices = new HashMap<Integer, Integer>();
        NodeComparator comp = new NodeComparator(minprices);
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(comp);
        Map<Integer, Map<Integer, Integer>> edges = new HashMap<Integer, Map<Integer, Integer>>();

        for (int[] flight : flights) {
            int s = flight[0];
            int d = flight[1];
            int weight = flight[2];
            if (edges.containsKey(s)) {
                Map<Integer, Integer> edge = edges.get(s);
                edge.put(d, weight);
            }
            else {
                Map<Integer, Integer> edge = new HashMap<Integer, Integer>();
                edge.put(d, weight);
                edges.put(s, edge);
            }
            minprices.put(s, Integer.MAX_VALUE);
            minprices.put(d, Integer.MAX_VALUE);
        }
        queue.offer(new int[]{src, 0, 0});
        minprices.put(src, 0);
        int min = Integer.MAX_VALUE;
        while(! queue.isEmpty()) {
            int[] p = queue.poll();
            int node = p[0];
            int dist = p[1];
            // Important !!! has to have node[2] to record weight, instead of minprices.get(node)
            // because sometimes, we may get a lower weight to node i, but the dist > K, so we need to keep record weights to target for each node in queue.
            int price = p[2];
            //System.out.println("get:" + node + "," + dist + "," + price);
            if (node == dst) {
                // Importat !!! can't return here, need to go through each edge and update the node and get min weight
                min = Math.min(min, p[2]);
                continue;
            }
            if (edges.containsKey(node) && edges.get(node) != null) {
                for (Integer neighbor : edges.get(node).keySet()) {
                    int weight = edges.get(node).get(neighbor);
                    if (price + weight < minprices.get(neighbor)) {
                        if (dist + 1 <= K + 1) { // satisfy dist costrainst
                            minprices.put(neighbor, price + weight);
                            //System.out.println(neighbor + "," + (price + weight));
                            queue.offer(new int[]{neighbor, dist + 1, price + weight});
                        }
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }
    public class NodeComparator implements Comparator<int[]> {
        Map<Integer, Integer> prices;
        public NodeComparator( Map<Integer, Integer> prices) {
            this.prices = prices;
        }
        public int compare(int[] i1, int[] i2) {
            // Important !!!! rank based on the distance instead of weight, weight is recorded using min
            return i1[1] - i2[1];
        }
    }
}
