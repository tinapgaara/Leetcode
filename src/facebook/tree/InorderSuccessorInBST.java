package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 12/5/17.
 *
 * 285. Inorder Successor in BST
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

 Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorInBST {

    public TreeNode inorderSuccessor_iteration(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while(root != null) {
            if (p.val < root.val) {
                // successor is on left part, or is root
                succ = root;
                root = root.left;
            }
            else {
                // successor must be right tree
                root = root.right;
            }
        }
        return succ;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            if (left == null) {
                return root;
            }
            else{
                return left;
            }
        }
    }

    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (p.val <= root.val) {
            return inorderPredecessor(root.left, p);
        }
        else {
            TreeNode right = inorderPredecessor(root.right, p);
            if (right == null) {
                return root;
            }
            else {
                return right;
            }
        }
    }
}
