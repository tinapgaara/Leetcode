package google.tree;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 11/1/15.
 */
/*
* Binary Search Tree Iterator

* Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next largest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*
*
* */
public class BSTInorderDecreaseIterator {

    Stack<TreeNode> stack = new Stack<TreeNode>();

    // Inorder traversal Iterator: next() return next largest number: decreasing number
    public BSTInorderDecreaseIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.right;
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
            if (top.left != null) {
                stack.push(top.left);
                top = top.left;
                while (top.right != null) {
                    top = top.right;
                    stack.push(top);
                }
            }
        }
        return res;
    }
}
