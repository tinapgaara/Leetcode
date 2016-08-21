package tree;

/**
 * Created by yingtan on 9/24/15.
 */
public class Successor {

    // For not BST tree. can not pass boundary case
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        TreeNode left = inorderSuccessor(root.left, p);
        if (p == left) return root; // judge left node
        if (p == root) { // judge current node
            if (root.right != null) return root.right;
            else
                return root;
        }
        TreeNode right = inorderSuccessor(root.right, p);// right
        return right;
    }

    // For BST ree
    public TreeNode inorderSuccessor_BST(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        if (p.val >= root.val) { // p's is in root's right sub-tree
            return inorderSuccessor(root.right, p);
        }

        TreeNode left = inorderSuccessor(root.left, p);// p is root, or in root's left sub-tree
        if (left == null) {
            return root;
        }
        else if (left.val < p.val) {
            return root;
        }
        else {
            return left;
        }
    }
}
