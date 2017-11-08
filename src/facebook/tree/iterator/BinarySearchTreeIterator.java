package facebook.tree.iterator;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 5/20/17.
 *
 * 173. Binary Search Tree Iterator Add to List
 DescriptionHintsSubmissionsSolutions
 Total Accepted: 86564
 Total Submissions: 213950
 Difficulty: Medium
 Contributor: LeetCode
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class BinarySearchTreeIterator {


    Stack<TreeNode> stack = new Stack<TreeNode>();

    public BinarySearchTreeIterator(TreeNode root) {
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
        TreeNode node = stack.pop();
        int res = node.val;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }
}
