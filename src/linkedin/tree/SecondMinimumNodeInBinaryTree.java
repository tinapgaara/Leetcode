package linkedin.tree;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/19/17.
 *
 * 671. Second Minimum Node In a Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:
 Input:
 2
 / \
 2   5
 / \
 5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.
 Example 2:
 Input:
 2
 / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.

 */
public class SecondMinimumNodeInBinaryTree {

    public int findSecondMinimumValue(TreeNode root) {
        if(root == null) return 0;
        int rootVal = root.val;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int secondMin = Integer.MAX_VALUE;
        boolean isSame = true;
        while(! queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val > root.val && cur.val < secondMin) {
                secondMin = cur.val;
                isSame = false;
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        if (isSame) {
            return -1;
        }
        else {
            return secondMin;
        }
    }
}
