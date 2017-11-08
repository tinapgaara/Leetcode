package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 3/28/17.
 */
public class DiameterBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int dia = depth(root.left) + depth(root.right);

        int leftDia = diameterOfBinaryTree(root.left);
        int rightDia = diameterOfBinaryTree(root.right);

        return Math.max(dia, Math.max(leftDia, rightDia));
    }

    public int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
