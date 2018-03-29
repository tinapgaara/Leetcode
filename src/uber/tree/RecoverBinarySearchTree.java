package uber.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/10/17.
 *
 * 99. Recover Binary Search Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Note:
 A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 */
public class RecoverBinarySearchTree {

    public void recoverTree(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        boolean flag = false;
        while(! flag) {
            prev[0] = null;
            flag = recur(root, prev);
        }
    }

    public boolean recur(TreeNode root, TreeNode[] prev) {
        if (root == null) return true;
        boolean leftCorrect = recur(root.left, prev);
        boolean curCorrect = true;
        if (prev[0] == null) prev[0] = root;
        else {
            if (root.val < prev[0].val) {
                int tmp = root.val;
                root.val = prev[0].val;
                prev[0].val = tmp;
                curCorrect = false;
            }
            prev[0] = root;
        }
        boolean rightCorrect = recur(root.right, prev);
        return leftCorrect && rightCorrect && curCorrect;
    }
}
