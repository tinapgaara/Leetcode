package google.tree.jiuzhang;

import tree.TreeNode;

/**
 * Created by yingtan on 10/29/17.
 *
 * 110. Balanced Binary Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {

    public class ResultType {
        int depth;
        boolean isBalance;
        public ResultType(int depth, boolean isBalance) {
            this.depth = depth;
            this.isBalance = isBalance;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return helper(root).isBalance;
    }

    public ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, true);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (! left.isBalance || ! right.isBalance) {
            return new ResultType(-1, false);
        }
        if (Math.abs(left.depth - right.depth) > 1) {
            return new ResultType(-1, false);
        }
        int depth = Math.max(left.depth, right.depth) + 1;
        return new ResultType(depth, true);
    }
}
