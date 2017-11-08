package google.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yingtan on 7/8/17.
 *
 */
public class BfsAlgBook {

    // Using adj list
    public class Node {
        // 0: white, 1: grey, 2:black
        int color;
        // how long between this node and source
        int dist;

        Node parent;

        List<Node> neighbors;

        public Node(int color, int dist) {
            this.color = color;
            this.dist = dist;
            neighbors = new ArrayList<>();
        }

    }

    /*
    * 1. Each node dequeue and enqueue at most once, total time in queue operations: o(V)
    * 2. Scan adj list only when a node is dequeued,
    *    everytime when a node is dequeued, its adj list will be scanned
    *    and each adj list is scanned only once
    *
    *    eg:
    *    1 -> [2, 3, 4]
    *    2 -> [3]
    *    3 -> [1, 4]
    *    4 -> [2]
    *
    *    total time to scan adj list:
    *    [2,3,4].size() + [3].size() + [1,4].size() + [2].size
    *    = # of edges = o(E)
    *
    *    Total time to scan adj list o(E)
    *
    * 3. Sometimes, need to init all nodes : o(V)
    *
    * 4. Total time complexity: o(V+E)
    * */

    public void Bfs(Node source) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(source);

        // o(E)
        while (! queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.neighbors != null) {
                // scan adj list, each adj list is scanned only once
                for (Node neighbor : cur.neighbors) {
                    // if the color is WHITE
                    if (neighbor.color == 0) {
                        // set color to GREY
                        neighbor.color = 1;
                        neighbor.dist = cur.dist + 1;
                        neighbor.parent = cur;

                        queue.offer(neighbor);
                    }
                }
            }
            // set color to BLACK
            cur.color = 2;
        }
    }

}
