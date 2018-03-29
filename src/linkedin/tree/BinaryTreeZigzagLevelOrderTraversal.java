package linkedin.tree;

import tree.TreeNode;

import java.util.*;

/**
 * Created by yingtan on 11/19/17.
 *
 * 103. Binary Tree Zigzag Level Order BinaryTreeVerticalOrderTraversal
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean odd = true;
        while(! queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            for (int i = 0 ; i < size; i ++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                level.add(cur.val);
            }
            if (! odd) {
                Collections.reverse(level);
                odd = true;
            }
            else {
                odd = false;
            }
            res.add(level);
        }
        return res;
    }
}
