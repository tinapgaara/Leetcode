package bloomberg.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 10/16/15.
 */
public class ValidBST {

    Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (isValidBST(root.left)) {
            if ((prev == null) || (prev.intValue() < root.val)) {
                prev = root.val;
                return isValidBST(root.right);
            }
            else
                return false;

        }
        return false;
    }
}
