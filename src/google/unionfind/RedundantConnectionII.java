package google.unionfind;

/**
 * Created by yingtan on 11/5/17.
 */
public class RedundantConnectionII {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        int[] multiParentEdge1 = {-1 ,1};
        int[] multiParentEdge2 = {-1, 1};
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            // -- >
            //       toNode   (multiple edges point to toNode)
            // ---> xxxx
            if (parent[to] > 0) {
                multiParentEdge1[0] = parent[to];
                multiParentEdge1[1] = to;
                multiParentEdge2[0] = from;
                multiParentEdge2[1] = to;

                // delete the later edge
                edge[0] = -1;
                edge[1] = -1;
            }
            else {
                parent[to] = from;
            }
        }
        for (int i = 0 ; i < parent.length; i ++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (edge[0] == -1 && edge[1] == -1) continue;

            int from = edge[0];
            int to = edge[1];
            int fromParent = findRootParent(to, parent);
            int toParent = findRootParent(from, parent);
            if (fromParent != toParent) {
                // union from to to
                parent[fromParent] = toParent;
            }
            else {
                // has circle
                if (multiParentEdge1[0] == -1) {
                    // no duplicate parent
                    return edge;
                }
                // has duplcate parent
                return multiParentEdge1;
            }
        }
        // no circle
        return multiParentEdge2;
    }

    private int findRootParent(int id, int[] parent) {
        if (parent[id] != id) {
            return findRootParent(parent[id], parent);
        }
        else {
            return id;
        }
    }
}
