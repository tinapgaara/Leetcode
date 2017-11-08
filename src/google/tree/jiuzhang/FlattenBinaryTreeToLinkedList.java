package google.tree.jiuzhang;

import tree.TreeNode;

/**
 * Created by yingtan on 10/29/17.
 *
 *
 Add to List
 114. Flatten Binary Tree to Linked List
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a binary tree, flatten it to a linked list in-place.

 For example,
 Given

 1
 / \
 2   5
 / \   \
 3   4   6
 The flattened tree should look like:
 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6
 */
public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        helper(root);
    }

    public TreeNode helper(TreeNode cur) {
        if (cur == null) return null;
        TreeNode leftLast = helper(cur.left);
        TreeNode rightLast = helper(cur.right);
        if (leftLast != null) {
            // important !!!!
            leftLast.right = cur.right;
            cur.right = cur.left;
            cur.left = null;
        }
        if (rightLast != null) {
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }
        // left, right both = null
        return cur;
    }


}
