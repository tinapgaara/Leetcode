package graph;

/**
 * Created by yingtan on 2/3/18.
 */
import java.util.*;
public class MST {
    public class Node {
        int nodeNo;
        int key;
        Node parent;
        public Node(int nodeNo, int key) {
            this.nodeNo = nodeNo;
            this.key = key;
            this.parent = null;
        }
    }
    public List<int[]> getMST(Map<Integer, Map<Integer, Integer>> graph) {
        boolean[] mstSet = new boolean[graph.size() + 1];
        List<int[]> res = new ArrayList<>();
        NodeComparator comp = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<>(comp);
        Map<Integer, Node> tmpMap = new HashMap<>();
        for (Integer node : graph.keySet()) {
            mstSet[node] = false;
            Node newNode = new Node(node, Integer.MAX_VALUE);
            tmpMap.put(node, newNode);
            queue.offer(newNode);
        }
        Node start = queue.peek();
        start.key = 0;
        start.parent = new Node(-1, Integer.MAX_VALUE);
        for (int i = 0; i < graph.size(); i ++) {
            // Get minKey node which are not in mstSet
            //Node node = queue.peek();
            Node node = queue.poll();
            // Step 1: Add curent minkey node to mstSet(something like visSet)
            int nodeNo = node.nodeNo;
            mstSet[nodeNo] = true;
            res.add(new int[]{node.parent.nodeNo, nodeNo});
            // Step 2: discover neighbor and update neighbors's nodes keys
            Map<Integer, Integer> edges = graph.get(nodeNo);
            for (Integer neighbor : edges.keySet()) {
                Integer weight = edges.get(neighbor);
                Node neighNode = tmpMap.get(neighbor);
                // update neighbors' nodes keys
                if (weight < tmpMap.get(neighbor).key) {
                    tmpMap.get(neighbor).key = weight;
                    tmpMap.get(neighbor).parent = node;
                    // has to re-offer to sort the heap again
                    queue.remove(neighNode);
                    queue.offer(neighNode);
                }
            }
        }
        return res;
    }
    public class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n1.key - n2.key;
        }
    }
    public static void main(String[] args) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<Integer, Map<Integer, Integer>>();
        Map<Integer, Integer> m = new HashMap<>();
        m.put(2,1);
        m.put(4,3);
        m.put(3,4);
        graph.put(1, m);

        m = new HashMap<>();
        m.put(4, 2);
        graph.put(2, m);
        m = new HashMap<>();
        m.put(4, 5);
        graph.put(3, m);
        m = new HashMap<>();
        m.put(1 , 3);
        graph.put(4, m);
        MST ob = new MST();
        ob.getMST(graph);
    }
}
