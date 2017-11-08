package google.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yingtan on 2/26/17.
 *
 * 133. Clone Graph
 */
public class CloneGraph {

    public class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();

        //HashMap<Integer, Boolean> vis = new HashMap<Integer, Boolean>();

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); // fill value

        map.put(newNode.label, newNode);

        dfs(node, newNode, map); // fill children

        return newNode;
    }

    public void dfs(UndirectedGraphNode node, UndirectedGraphNode newNode,  HashMap<Integer, UndirectedGraphNode> map
    ) {

        // copy all children
        List<UndirectedGraphNode> newNeighbors = new ArrayList<UndirectedGraphNode>();

        //vis.put(newNode.label, true);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode newNeighbor;
            // not visited
            //if (vis.get(neighbor.label) == null) { can work as well
            if (! map.containsKey(neighbor.label)) {

                newNeighbor = new UndirectedGraphNode(neighbor.label); // fill value

                map.put(neighbor.label, newNeighbor);

                newNeighbors.add(newNeighbor); // important !!

                dfs(neighbor, newNeighbor, map);

            }
            else {
                newNeighbor = map.get(neighbor.label);
                newNeighbors.add(newNeighbor);
            }
        }
        newNode.neighbors = newNeighbors;
    }
}
