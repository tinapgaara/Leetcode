package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/27/15.
 */
/*
* Find the distance between two keys in a binary tree,
* no parent pointers are given.
* Distance between two nodes is the minimum number of edges to be traversed to
* reach one node from other.
* */
public class DistanceBetweenTwoNodes {

    public int distance(TreeNode root, TreeNode node1, TreeNode node2) {
        int depth1 = depth(root, node1);
        int depth2 = depth(root, node2);
        int depthAncestor = depth(root, commonAncestor(root, node1, node2));

        return depth1 + depth2 - 2*depthAncestor;
    }

    public int depth(TreeNode root, TreeNode cur) {
        if (root == null) {
            return -1;
        }
        else if (root == cur) {
            return 0;
        }
        int depthLeft = depth(root.left, cur);
        if (depthLeft != -1) {
            return depthLeft + 1;
        }
        else {
            int depthRight = depth(root.right, cur);
            return depthRight + 1;
        }
    }

    public TreeNode commonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
        if ((n1 == null) && (n2 == null)) {
            return null;
        }
        TreeNode leftAncestor = commonAncestor(root.left, n1, n2);
        TreeNode rightAncestor = commonAncestor(root.right, n1, n2);

        if ((root == leftAncestor) || (root == rightAncestor)) {
            return root;
        }

        if ((leftAncestor == null) && (rightAncestor == null)) {
            return null;
        }
        else if (leftAncestor != null) {
            return  leftAncestor;
        }
        else if (rightAncestor != null) {
            return rightAncestor;
        }
        else {
            return root;
        }
    }
}
