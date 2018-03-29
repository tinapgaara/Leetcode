package facebook.graph;

import graph.UndirectedGraphNode;

import java.util.*;

/**
 * Created by yingtan on 12/20/17.
 *
 * 133. Clone Graph
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


 OJ's undirected graph serialization:
 Nodes are labeled uniquely.

 We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 The graph has a total of three nodes, and therefore contains three parts as separated by #.

 First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 Second node is labeled as 1. Connect node 1 to node 2.
 Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:

 1
 / \
 /   \
 0 --- 2
 / \
 \_/
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> originalToCopy = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        UndirectedGraphNode rootCopy = new UndirectedGraphNode(node.label);
        originalToCopy.put(node, rootCopy);
        queue.offer(node);
        while(! queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            // because we check if vis neighbor instead of root, we must already copy cur node, so can just get it from map
            UndirectedGraphNode copyCur = originalToCopy.get(cur);
            if (cur.neighbors != null) {
                // check neighbor
                for (UndirectedGraphNode neighbor : cur.neighbors) {
                    if (originalToCopy.containsKey(neighbor)) {
                        // already vis here before
                        UndirectedGraphNode neighborCopy = originalToCopy.get(neighbor);
                        // but never build this link
                        copyCur.neighbors.add(neighborCopy);
                    }
                    else {
                        // this is the new node to vis
                        UndirectedGraphNode neighborCopy = new UndirectedGraphNode(neighbor.label);
                        originalToCopy.put(neighbor, neighborCopy);
                        copyCur.neighbors.add(neighborCopy);
                        // offer to queue
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return rootCopy;
    }

}
