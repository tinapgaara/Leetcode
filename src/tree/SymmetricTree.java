package tree;

/**
 * Created by yingtan on 10/28/15.
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recurIsSymmetric(root.left, root.right);
    }

    public boolean recurIsSymmetric(TreeNode leftNode, TreeNode rightNode) {
        if ((leftNode == null) && (rightNode == null)) {
            return true;
        }
        else if ((leftNode == null) && (rightNode != null)) {
            return false;
        }
        else if ((leftNode != null) && (rightNode == null)) {
            return false;
        }
        else {
            if (leftNode.val == rightNode.val) {
                if (recurIsSymmetric(leftNode.left, rightNode.right)) {
                    if (recurIsSymmetric(leftNode.right, rightNode.left)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
