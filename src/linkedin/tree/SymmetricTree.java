package linkedin.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/19/17.
 *
 * 101. Symmetric Tree
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSym(root.left, root.right);
    }

    public boolean isSym(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null && t2 != null) return false;
        if (t1 != null && t2 == null) return false;
        if (t1 != null && t2 != null) {
            if (t1.val == t2.val) {
                return (isSym(t1.left, t2.right) && isSym(t1.right, t2.left));
            }
        }
        return false;
    }
}
