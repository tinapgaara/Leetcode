package bloomberg.tree;

import tree.TreeNode;

/**
 * Created by max2 on 10/15/15.
 */
/*
*    1             1
* 2     3       3    2
*
*
* */

public class MirrorTree {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        node2.left = node5;
        node2.right = node4;

        MirrorTree tree = new MirrorTree();
        tree.mirrorTree(node1);
    }
}
