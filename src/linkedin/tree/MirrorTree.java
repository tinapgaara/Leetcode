package linkedin.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/29/17.
 */
public class MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
