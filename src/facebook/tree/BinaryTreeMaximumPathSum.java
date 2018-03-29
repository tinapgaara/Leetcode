package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 12/5/17.
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
 Return 6.


 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxSumStartsFrom(root, max);
        return max[0];
    }

    public int maxSumStartsFrom(TreeNode root, int[] max) {
        if (root == null) return 0;
        int left = maxSumStartsFrom(root.left, max);
        int right = maxSumStartsFrom(root.right, max);

        int maxStartsFromCur = Math.max(root.val, Math.max(left, right) + root.val);
        max[0] = Math.max(max[0], Math.max(maxStartsFromCur, left + right + root.val));
        return maxStartsFromCur;
    }
}
