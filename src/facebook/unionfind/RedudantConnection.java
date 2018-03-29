package facebook.unionfind;

/**
 * Created by yingtan on 3/18/18.
 *
 * 684. Redundant Connection
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 In this problem, a tree is an undirected graph that is connected and has no cycles.

 The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

 The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

 Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

 Example 1:
 Input: [[1,2], [1,3], [2,3]]
 Output: [2,3]
 Explanation: The given undirected graph will be like this:
 1
 / \
 2 - 3
 Example 2:
 Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 Output: [1,4]
 Explanation: The given undirected graph will be like this:
 5 - 1 - 2
 |   |
 4 - 3
 */
public class RedudantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        if (edges == null || edges.length == 0) {
            return res;
        }
        int nodeNum = edges.length;
        int[] parent = new int[nodeNum + 1];
        for (int i = 0; i < edges.length; i ++) {
            parent[i + 1] = (i + 1);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int fromParent = findParent(parent, from);
            int toParent = findParent(parent, to);
            if (fromParent != toParent) {
                // Important !!! union parents instead of union from and to
                // eg: {1,4},{3,4},{1,3}, {1,2}, {4, 5}
                union(parent, fromParent, toParent);
            }
            else {
                // this is redundant edge
                res[0] = from;
                res[1] = to;
                break;
            }
        }
        return res;
    }
    public int findParent(int[] parent, int node) {
        while(parent[node] != node) {
            // node's parent points to node's parent's parent
            parent[node] = parent[parent[node]];
            node = parent[node];
        }
        return node;
    }
    public void union(int[] parent, int target, int unioned) {
        parent[unioned] = target;
    }
}
