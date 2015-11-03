package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/3/15.
 */
public class MinDepthTree {

    public int minDepth(TreeNode root) {
        if (root == null) return  0;
        if ((root.left == null) && (root.right == null)) return 1;
        else if ((root.left == null) && (root.right != null))
            return minDepth(root.right) + 1;
        else if ((root.left != null) && (root.right == null))
            return minDepth(root.left) + 1;
        else {
            int minLeft = minDepth(root.left);
            int minRight = minDepth(root.right);

            return Math.min(minLeft, minRight) + 1;
        }
    }
}
