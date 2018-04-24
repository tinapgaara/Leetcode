package apple.tree;

import tree.TreeNode;

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (recurBalanced(root) == -1) return false;
        else return true;
    }
    public int recurBalanced(TreeNode root) {
        if (root == null) return 0;
        int left = recurBalanced(root.left);
        if (left == -1) {
            // not balanced
            return -1;
        }
        int right = recurBalanced(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        }
        else {
            return -1;
        }
    }
}
