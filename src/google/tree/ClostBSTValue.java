package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 2/26/17.
 *
 * 270. Closest Binary Search Tree Value
 */
public class ClostBSTValue {

    public int closestValue(TreeNode root, double target) {
        // if current val < target, go right
        // if current val > target, go left

        int prevVal = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(prevVal - target)) { // keep record which is the clost value node
                prevVal = root.val;
            }
            if (root.val < target) {
                root = root.right;
            }
            else if (root.val > target) {
                root = root.left;
            }
            else
                return root.val;
        }
        return prevVal;
    }
}
