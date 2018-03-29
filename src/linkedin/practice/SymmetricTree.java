package linkedin.practice;

import tree.TreeNode;

/**
 * Created by yingtan on 11/22/17.
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode cur) {
        if (cur == null) return true;
        return isSym(cur.left, cur.right);
    }

    public boolean isSym(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left != null && right == null) return false;
        else if (left == null && right != null) return false;
        else if (left.val != right.val) return false;
        else return (isSym(left.left, right.right) && isSym(left.right, right.left));
    }

    public TreeNode generateSymmetricTree(TreeNode cur) {
        if (cur == null) return null;
        TreeNode left = cur.right;
        TreeNode rigjt = cur.left;
        TreeNode sym = new TreeNode(cur.val);
        sym.left = left;
        sym.right = rigjt;
        return sym;
    }
}
