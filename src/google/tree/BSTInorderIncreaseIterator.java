package google.tree;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 11/1/15.
 */
/*

Binary Search Tree Iterator

* Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*
* */
public class BSTInorderIncreaseIterator {

    Stack<TreeNode> stack = new Stack<TreeNode>();

    // Inorder traversal Iterator, next() return next smallest number
    public BSTInorderIncreaseIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        int res = 0;
        if (hasNext()) {
            TreeNode top = stack.pop();
            res = top.val;
            if (top.right != null) {
                stack.push(top.right);
                top = top.right;
                while (top.left != null) {
                    top = top.left;
                    stack.push(top);
                }
            }
        }
        return res;
    }
}
