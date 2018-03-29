package linkedin.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/20/17.
 *
 * 56. Binary Tree Upside Down
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

 For example:
 Given a binary tree {1,2,3,4,5},
 1
 / \
 2   3
 / \
 4   5
 return the root of the binary tree [4,5,2,#,#,3,1].
 4
 / \
 5   2
 / \
 3   1
 confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 */
public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode next = null;
        TreeNode prev = null;
        TreeNode right = null;
        while(cur != null) {
            next = cur.left;
            cur.left = right;
            right = cur.right;
            cur.right = prev;

            prev = cur;
            cur = next;
        }
        return prev;
    }
}
