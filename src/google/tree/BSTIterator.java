package google.tree;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 2/26/17.
 *
 * 173. Binary Search Tree Iterator
 *
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class BSTIterator {

    // using stack
    Stack<TreeNode> stack = new Stack<TreeNode>();

    // keep push left node
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (stack.isEmpty()) return false;
        else return true;
    }

    /** @return the next smallest number */
    public int next() {
        // firstly, pop up the node from stack, this is the smallest one
        TreeNode node = stack.pop();
        int res = node.val;
        // then, if this node has right children
        if (node.right != null) {
            node = node.right;
            // keep push left nodes of this right children
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }
}
