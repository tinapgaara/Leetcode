package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by yingtan on 10/6/15.
 */
/*
* Focus on shortest path.
* */
public class BFS {

    Map<Node, List<Node>> graph;

    public void BFS( int startNode) {

        Queue<Node> queue = new LinkedList<Node>();

        queue.offer(new Node(startNode, 0, null, 1));

        if ( ! queue.isEmpty()) {
            Node node = queue.poll();
            for (Node neighbor : graph.get(node)) {
                if (neighbor.mColor == 0) { // white

                    neighbor.mColor = 1;
                    neighbor.mDist = node.mDist + 1;
                    neighbor.mPi = node;

                    queue.offer(neighbor);

                }
            }
            node.mColor = 2;//black
        }
    }

    public void buildGraph() {
        /*
        * For each node:
        * // dist = Integer.MAX_VALUE  ; pi = null ; color = WHITE
        * Node node = new Node(val, Integer.MAX_VALUE, null, 0);
        *
        * */
    }
}
