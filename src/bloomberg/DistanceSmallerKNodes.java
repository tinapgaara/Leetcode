package bloomberg;

import java.util.*;

/**
 * Created by yingtan on 10/26/15.
 */
public class DistanceSmallerKNodes {

    Map<Integer, List<Integer>> edges;

    public List<Integer> findNodesSmallerK (int nodeNo, int k) {

        int nodeNum = edges.size();
        int[] colors = new int[nodeNum + 1];

        List<Integer> res = new ArrayList<>();

        Queue<Integer> nodes = new LinkedList<>();
        Queue<Integer> dist = new LinkedList<>();
        nodes.offer(nodeNo);
        dist.offer(1);

        while (! nodes.isEmpty()) {
            nodeNo = nodes.poll();
            int dis = dist.poll();

            if (dis < k) res.add(dis);
            else break;

            colors[nodeNo] = 1; // grey
            List<Integer> neighbors = edges.get(nodeNo);
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
                    if (colors[neighbor] == 0) {
                        nodes.offer(neighbor);
                        dist.offer(dis + 1);
                    }
                }
            }
            colors[nodeNo] = 2; // black
        }
        return res;
    }

    public static void main(String[] args) {
        DistanceSmallerKNodes ob = new DistanceSmallerKNodes();
        ob.edges = new HashMap<>();
    }
}
