package facebook.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 5/20/17.
 *
 * 236. Lowest Common Ancestor of a Binary Tree Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 98783
 Total Submissions: 333578
 Difficulty: Medium
 Contributor: LeetCode
 Given a binary tree, find the lowest common ancestor (LowestCommonAncestor) of two given nodes in the tree.

 According to the definition of LowestCommonAncestor on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

 _______3______
 /              \
 ___5__          ___1__
 /      \        /      \
 6      _2       0       8
 /  \
 7   4
 For example, the lowest common ancestor (LowestCommonAncestor) of nodes 5 and 1 is 3. Another example is LowestCommonAncestor of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LowestCommonAncestor definition.
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode leftCommonAncestor = lowestCommonAncestor(root.left, p , q);
        TreeNode rightCommonAncestor = lowestCommonAncestor(root.right, p, q);
        if (root == p || root == q) return root;

        if (leftCommonAncestor == null && rightCommonAncestor == null) {
            return null;
        }
        else if (leftCommonAncestor != null && rightCommonAncestor != null) {
            return root;
        }
        else if (leftCommonAncestor != null) {
            return leftCommonAncestor;
        }
        else if (rightCommonAncestor != null)
            return rightCommonAncestor;

        return null;
    }
}
