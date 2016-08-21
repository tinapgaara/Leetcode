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
            return recurCount(root);
        }

        public int recurCount(TreeNode root) {
            if (root == null) return 0;
            int leftDepth = countLeftDepth(root);
            int rightDepth = countRightDepth(root);
            if (leftDepth == rightDepth) {
                return ((1 << leftDepth) - 1);
            }
            else {
                return recurCount(root.left) + 1 + recurCount(root.right);
            }
        }

        public int countLeftDepth(TreeNode root) {
            int depth = 0;
            TreeNode cur = root;
            while (cur != null) {
                depth ++;
                cur = cur.left;
            }
            return depth;
        }

        public int countRightDepth(TreeNode root) {
            int depth = 0;
            TreeNode cur = root;
            while (cur != null) {
                depth ++;
                cur = cur.right;
            }
            return depth;
        }
}
