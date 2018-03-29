package linkedin.practice;

import tree.TreeNode;

/**
 * Created by yingtan on 11/22/17.
 */
public class LowestCommonAncestorBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }
        else if (left != null) {
            return left;
        }
        else {
            return right;
        }
    }
}
