package tree;

/**
 * Created by yingtan on 3/17/18.
 */
public class BinarySearchTreeSuccessor {
    // Find the first node >= val. 
    public TreeNode inorderSuccessor_recur(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) {
            TreeNode left = inorderSuccessor_recur(root.left, val);
            if (left == null) {
                return root;
            }
            else {
                return left;
            }
        }
        else {
            return inorderSuccessor_recur(root.right, val);
        }
    }
    public TreeNode inorderSuccessor_iter(TreeNode root, int val) {
        TreeNode cur = root;
        TreeNode succ = null;
        while(cur != null) {
            if (val > cur.val) {
                cur = cur.right;
            }
            else {
                succ = cur;
                cur = cur.left;
            }
        }
        return succ;
    }
}
