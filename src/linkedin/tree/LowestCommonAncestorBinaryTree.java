package linkedin.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/28/17.
 */
public class LowestCommonAncestorBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        TreeNode leftCommonAncestor = lowestCommonAncestor(root.left, p , q);
        TreeNode rightCommonAncestor = lowestCommonAncestor(root.right, p, q);

        if (root == p || root == q) return root;

        if (leftCommonAncestor == null && rightCommonAncestor == null) {
            return null;
        }
        else if (leftCommonAncestor != null && rightCommonAncestor != null) {
            return root;
        }
        else if (leftCommonAncestor != null) {
            return leftCommonAncestor;
        }
        else if (rightCommonAncestor != null)
            return rightCommonAncestor;

        return null;
    }
}
