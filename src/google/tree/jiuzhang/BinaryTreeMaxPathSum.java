package google.tree.jiuzhang;

import tree.TreeNode;

/**
 * Created by yingtan on 10/29/17.
 *
 * 124. Binary Tree Maximum Path Sum
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

 For example:
 Given the below binary tree,

 1
 / \
 2   3
 */
public class BinaryTreeMaxPathSum {

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSumStartFrom(root, max);
        return max[0];
    }

    public int maxPathSumStartFrom(TreeNode root, int[] max) {
        if (root == null) return 0;
        int leftSum = maxPathSumStartFrom(root.left, max);
        int rightSum = maxPathSumStartFrom(root.right, max);
        /*
          /
        /    \
        */
        max[0] = Math.max(max[0], leftSum + rightSum + root.val);
        /*
          /
        /
        */
        max[0] = Math.max(max[0], leftSum + root.val);
        /*
        \
          \
        */
        max[0] = Math.max(max[0], rightSum + root.val);

        max[0] = Math.max(max[0], root.val);

        return Math.max(Math.max(leftSum + root.val, rightSum + root.val), root.val);
    }
}
