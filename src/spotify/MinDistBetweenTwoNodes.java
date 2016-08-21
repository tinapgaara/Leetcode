package spotify;

import tree.TreeNode;

/**
 * Created by yingtan on 12/1/15.
 */
public class MinDistBetweenTwoNodes {

    public int distance(TreeNode root, TreeNode n1, TreeNode n2) {
        TreeNode LCA = lowestCommonAncestor(root, n1, n2);
        int n1Depth = depth(root, n1);
        int n2Depth = depth(root, n2);
        int LCADepth = depth(root, LCA);

        return n1Depth + n2Depth - 2 * LCADepth;
    }

    public int depth(TreeNode root, TreeNode node) {
        if (root == null)
            return -1;
        if (root == node) {
            return 1;
        }

        int left = depth(root.left, node);
        if (left != -1) {
            return left + 1;
        }
        else {
            int right = depth(root.right, node);
            return right + 1;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(root.left, n1, n2);
        TreeNode right = lowestCommonAncestor(root.right, n1, n2);

        if ((root == left) || (root == right)) {
            return root;
        }
        if ((left == null) && (right == null)) {
            return null;
        }
        if ((left != null) && (right != null)) {
            return root;
        }
        if (left != null) {
            return left;
        }
        else
            return right;
    }
}
