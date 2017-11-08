package google.graph;

import graph.UndirectedGraphNode;

import java.util.*;

/**
 * Created by yingtan on 9/23/15.
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        // stores all new nodes
        // oldNode -> newNode
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); // fill value

        map.put(node, newNode);

        // Solution 1: using dfs, copy node, copy node's one children, copy node's one children's one children
        dfs(node, newNode, map); // copy children


        // Solution 2: using bfs, copy nodes level by level
        //  queue: stories all original nodes
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        //bfs(queue, map); // copy children

        return newNode;
    }


    public void bfs(Queue<UndirectedGraphNode> queue, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        while(! queue.isEmpty()) {
            UndirectedGraphNode oldNode = queue.poll();
            List<UndirectedGraphNode> oldNeighbors = oldNode.neighbors;

            for (UndirectedGraphNode oldNeighbor : oldNeighbors) {

                if (! map.containsKey(oldNeighbor)) {
                    UndirectedGraphNode newNeighbor = new UndirectedGraphNode(oldNeighbor.label);

                    map.put(oldNeighbor, newNeighbor);

                    // point newnode's neighbor to newNeighbor
                    UndirectedGraphNode newNode = map.get(oldNode);
                    newNode.neighbors.add(newNeighbor);

                    queue.offer(oldNeighbor);
                }
                else {
                    UndirectedGraphNode newNeighbor = map.get(oldNeighbor);
                    // point newNode's neighbor to newNeighbor
                    UndirectedGraphNode newNode = map.get(oldNode);
                    newNode.neighbors.add(newNeighbor);
                }
            }
        }
    }

    // copy oldNode's neighbors to newNode, link newNode -> newNode's neighbor
    public void dfs(UndirectedGraphNode oldNode, UndirectedGraphNode newNode,  HashMap<UndirectedGraphNode, UndirectedGraphNode> map
    ) {

        // copy all children
        List<UndirectedGraphNode> newNeighbors = new ArrayList<UndirectedGraphNode>();

        for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
            UndirectedGraphNode newNeighbor;
            // haven't visit original nodes' neighbor
            if (! map.containsKey(oldNeighbor)) {

                newNeighbor = new UndirectedGraphNode(oldNeighbor.label); // fill value

                map.put(oldNeighbor, newNeighbor);

                newNeighbors.add(newNeighbor); // important !!

                dfs(oldNeighbor, newNeighbor, map);

            }
            // already visited original node, then don't need to do dfs
            else {
                newNeighbor = map.get(oldNeighbor);
                newNeighbors.add(newNeighbor);
            }
        }
        // important
        newNode.neighbors = newNeighbors;
    }
}
