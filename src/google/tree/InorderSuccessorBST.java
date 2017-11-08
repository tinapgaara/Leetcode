package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 2/27/17.
 *
 * 285. Inorder Successor in BST Add to List
 Description  Submission  Solutions
 Total Accepted: 26713
 Total Submissions: 73908
 Difficulty: Medium
 Contributors: Admin
 Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

 Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorBST {

    TreeNode prev = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if ( (root == null) || (p == null) ) return null;

        TreeNode left = inorderSuccessor(root.left, p);

        if (left != null) return left;

        if (prev != null) {
            if (prev == p) {
                return root;
            }
        }
        prev = root;

        return inorderSuccessor(root.right, p);

    }
}
