package google.graph.jiuzhang;

import graph.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by yingtan on 10/29/17.
 *
 *
 Add to List
 133. Clone Graph
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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
  /  \
 /   \
 0 --- 2
 / \
 \_/
 */
public class CloneGraph {

    // clone graph using BFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        // write your code here
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        UndirectedGraphNode copyRoot = new UndirectedGraphNode(node.label);
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        map.put(node, copyRoot);
        queue.offer(node);

        while(! queue.isEmpty()) {
            UndirectedGraphNode oldNode = queue.poll();
            UndirectedGraphNode copyNode = map.get(oldNode);
            for (UndirectedGraphNode neighbor : oldNode.neighbors) {
                if (! map.containsKey(neighbor)) {
                    UndirectedGraphNode copyNeighbor = new UndirectedGraphNode(neighbor.label);
                    copyNode.neighbors.add(copyNeighbor);
                    map.put(neighbor, copyNeighbor);
                    queue.offer(neighbor);
                }
                else {
                    UndirectedGraphNode copyNeighbor = map.get(neighbor);
                    copyNode.neighbors.add(copyNeighbor);
                }
            }
        }
        return copyRoot;
    }
}
