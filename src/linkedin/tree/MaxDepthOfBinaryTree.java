package linkedin.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/28/17.
 */
public class MaxDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);

        return Math.max(maxLeft, maxRight) + 1;
    }
}
