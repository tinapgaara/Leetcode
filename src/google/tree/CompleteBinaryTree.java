package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/7/15.
 */
public class CompleteBinaryTree {

    // if leftchild == null, then rightchild == null as well.
    public boolean isCompleteTree(TreeNode root) {
        if (root != null) {
            if (root.left == null) {
                if (root.right != null) return false;
                else return true;
            }
            boolean left = isCompleteTree(root.left);
            boolean right = isCompleteTree(root.right);
            return left & right;
        }
        return true;
    }

    public int countCompleteTreeNodes(TreeNode root) {
        return 0;
        // TODO
    }
}
