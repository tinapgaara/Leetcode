package google.graph;

import graph.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 9/23/15.
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, Integer> nodeflags = new HashMap<Integer, Integer>();
        // null: white  0: grey 1:black
        Map<Integer, UndirectedGraphNode> nodes = new HashMap<Integer, UndirectedGraphNode>();

        if (node == null) return null;
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        nodes.put(copy.label, copy);

        DFS(node, copy, nodeflags, nodes);

        return copy;
    }
    public void DFS(UndirectedGraphNode curNode, UndirectedGraphNode copy,
                    Map<Integer, Integer> nodeflags, Map<Integer, UndirectedGraphNode> nodes) {

        List<UndirectedGraphNode> neighbors = curNode.neighbors;
        List<UndirectedGraphNode> copyNeighbors = new ArrayList<UndirectedGraphNode>();
        nodeflags.put(curNode.label, new Integer(0)); //grey
        for (UndirectedGraphNode neigh : neighbors) {
            if (nodeflags.get(neigh.label) == null) {// white
                UndirectedGraphNode copyNextNode = new UndirectedGraphNode(neigh.label);
                copyNeighbors.add(copyNextNode);
                nodes.put(neigh.label, copyNextNode);
                DFS(neigh, copyNextNode, nodeflags, nodes);
            }
            else if (nodeflags.get(neigh.label) != null) { // grey or black
                UndirectedGraphNode hasCopiedNode = nodes.get(neigh.label);
                copyNeighbors.add(hasCopiedNode);
            }
        }
        nodeflags.put(curNode.label, new Integer(1));
        copy.neighbors = copyNeighbors;
    }
}
