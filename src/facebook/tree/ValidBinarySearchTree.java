package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 12/20/17.
 */
public class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val > min && root.val < max) {
            return isValid(root.left, min, (long)root.val) && isValid(root.right, (long)root.val, max);
        }
        else {
            return false;
        }
    }

}
