package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 3/17/18.
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (height(root) == -1) {
            return false;
        }
        return true;
    }
    public int height(TreeNode cur) {
        int leftH = height(cur.left);
        int rightH = height(cur.right);
        if (rightH != -1 && leftH != -1 && Math.abs(leftH - rightH) <= 1) {
            // this is balanced tree
            return Math.max(leftH, rightH) + 1;
        }
        else {
            return -1;
        }
    }
}
