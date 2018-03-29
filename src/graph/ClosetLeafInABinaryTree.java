package graph;

import tree.TreeNode;

/**
 * Created by yingtan on 3/4/18.
 *
 * 742. Closest Leaf in a Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

 Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

 In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

 Example 1:

 Input:
 root = [1, 3, 2], k = 1
 Diagram of binary tree:
 1
 / \
 3   2

 Output: 2 (or 3)

 Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
 Example 2:

 Input:
 root = [1], k = 1
 Output: 1

 Explanation: The nearest leaf node is the root node itself.
 Example 3:

 Input:
 root = [1,2,3,4,null,null,null,5,null,6], k = 2
 Diagram of binary tree:
 1
 / \
 2   3
 /
 4
 /
 5
 /
 6

 Output: 3
 Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
 Note:
 root represents a binary tree with at least 1 node and at most 1000 nodes.
 Every node has a unique node.val in range [1, 1000].
 There exists some node in the given binary tree for which node.val == k.
 */
import java.util.*;
public class ClosetLeafInABinaryTree {

    public int findClosestLeaf(TreeNode root, int k) {
        if (root == null) return 0;
        Map<Integer, TreeNode> nodesMap = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> vis = new HashSet<>();
        // translate a tree to graph
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int val = cur.val;
            if (! nodesMap.containsKey(val)) {
                nodesMap.put(val, cur);
            }
            TreeNode left = cur.left;
            // build edge: val -> left, left ->val
            if (left != null) {
                if (graph.containsKey(val)) {
                    graph.get(val).add(left.val);
                }
                else {
                    Set<Integer> neighbors = new HashSet<>();
                    neighbors.add(left.val);
                    graph.put(val, neighbors);
                }
                if (graph.containsKey(left.val)) {
                    graph.get(left.val).add(val);
                }
                else {
                    Set<Integer> neighbors = new HashSet<>();
                    neighbors.add(val);
                    graph.put(left.val, neighbors);
                }
                queue.offer(left);
            }
            // build edge: val -> right, right ->val
            TreeNode right = cur.right;
            if (right != null) {
                if (graph.containsKey(val)) {
                    graph.get(val).add(right.val);
                }
                else {
                    Set<Integer> neighbors = new HashSet<>();
                    neighbors.add(right.val);
                    graph.put(val, neighbors);
                }
                if (graph.containsKey(right.val)) {
                    graph.get(right.val).add(val);
                }
                else {
                    Set<Integer> neighbors = new HashSet<>();
                    neighbors.add(val);
                    graph.put(right.val, neighbors);
                }
                queue.offer(right);
            }
        }
        // starts from the k.
        Queue<Integer> queueNode = new LinkedList<>();
        queueNode.offer(k);
        // bfs
        while(! queueNode.isEmpty()) {
            Integer cur = queueNode.poll();
            TreeNode curnode = nodesMap.get(cur);
            // we hit leaf
            if (curnode.left == null && curnode.right == null) {
                // hit the closet node
                return cur;
            }
            if (graph.get(cur) != null) {
                for (Integer neighbor : graph.get(cur)) {
                    if (! vis.contains(neighbor)) {
                        vis.add(neighbor);
                        queueNode.offer(neighbor);
                    }
                }
            }
        }
        return -1;
    }
}
