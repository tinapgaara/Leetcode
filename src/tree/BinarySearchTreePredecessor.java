package tree;

/**
 * Created by yingtan on 3/17/18.
 */
public class BinarySearchTreePredecessor {
    public TreeNode inorderPrecessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (p.val <= root.val) {
            return inorderPrecessor(root.left, p);
        }
        else {
            TreeNode right = inorderPrecessor(root.right, p);
            if (right == null) {
                return root;
            }
            else {
                return right;
            }
        }
    }
    // time: o(h) space: o(1)
    public TreeNode inorderPrecessor_iteration(TreeNode root, TreeNode p) {
        TreeNode prev = null;
        // approaching p (target)
        while(root != null) {
            // each time whe hit a node whose value is larger tan the target, mark this as successor
            if (p.val > root.val) {
                prev = root;
                root = root.right;
            }
            else {
                root = root.left;
            }
        }
        return prev;
    }

}
