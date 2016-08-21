package vmware;

import tree.TreeNode;

/**
 * Created by yingtan on 10/28/15.
 */
public class ValidBST {

    Integer prevVal = null;
    // Solution 1: use prev pointer
    public boolean isValidBST(TreeNode node) {

        if (node == null) {
            return true;
        }
        else {
            if (isValidBST(node.left)) {
                if ((prevVal == null) || (node.val > prevVal.intValue())) {
                    prevVal = node.val;
                    return isValidBST(node.right);
                }
            }
        }
        return false;
    }

    // Solution 2; use range
    public boolean isValidBST_2(TreeNode root) {
        return recurIsValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    public boolean recurIsValidBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if ((node.val < max) && (node.val > min)) {
            long leftMax = (long)node.val;
            long rightMin = (long)node.val;
            if (recurIsValidBST(node.left, min, leftMax) &&
                    recurIsValidBST(node.right, rightMin, max)) {
                return true;
            }
        }
        return false;
    }
}
