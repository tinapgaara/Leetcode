package google.unionfind;

/**
 * Created by yingtan on 11/4/17.
 *
 * 684. Redundant Connection
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
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
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[2001];
        for (int i = 0 ; i < parent.length; i ++) {
            parent[i] = i;
        }
        /*  Union Find  http://www.geeksforgeeks.org/union-find/
        0   1   2
        0   1   2
        Process edge 0-1 : union 0 and 1
        0   1   2
        1   1   2
        Process edge 1-2 : union 1 and 2
        0   1   2
        1   2   2
        Process edge 0-2: find out they both point to 2,
        Edge 0-2: 0 is in subset 2 and 2 is also in subset 2. Hence, including this edge forms a cycle.
        */
        for (int[]  edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int fromParent = findParent(parent, from);
            int toParent = findParent(parent, to);
            if (fromParent != toParent) {
                // union fromNode and toNode
                parent[fromParent] = toParent;
            }
            else {
                return edge;
            }
        }
        return new int[2];
    }

    // A utility function to find the subset of an element i
    private int findParent(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return findParent(parent, parent[i]);
    }
}
