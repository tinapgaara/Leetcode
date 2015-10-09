package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 10/7/15.
 */
public class TopologicalSort {

    Map<Node, List<Node>> graph;
    public int time;

    public LinkedList<Node> res;

    public LinkedList<Node> topoSort() {
        time= 0;
        res = new LinkedList<>();
        for (Map.Entry<Node, List<Node>> entry : graph.entrySet()) {
            Node node = entry.getKey();
            if (node.mColor == 0) {
                DFSVisit(node, res);
            }
        }
        return res;
    }

    public void DFSVisit(Node node, LinkedList<Node> res) {
        time ++;
        node.mColor = 1;
        node.mDiscover = time;
        List<Node> neighbors = graph.get(node);
        for (Node neighbor: neighbors) {
            if (neighbor.mColor == 0) {
                DFSVisit(neighbor, res);
            }
        }
        node.mColor = 2;
        time ++;
        node.mFinish = time;
        res.addFirst(node);
    }
}
