package google.tree;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by yingtan on 11/20/15.
 */
/*
* Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?
*
* */
public class KthSmallestNodeBST {

    // Solution 1.1: inorder traverse, using  stack, using loop
    // o(n)
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> nodes = new Stack<TreeNode>();

        TreeNode cur = root;
        while ((nodes != null) || (cur != null)) {
            if (cur != null) {
                nodes.push(cur);
                cur = cur.left;
            }
            else {
                TreeNode top = nodes.pop();
                k --;
                if (k == 0) return top.val;
                cur = top.right;
            }
        }
        return 0;
    }

    // Solution 1.2: use recursive
    public int kthSmallest_recur(TreeNode root, int k) {

        int[] smallestVal = new int[1];
        smallestNodeNo(root, 0, k, smallestVal);
        return smallestVal[0];

    }

    public int smallestNodeNo(TreeNode root, int no, int k, int[] smallestVal) {
        if (root == null) {
            return no;
        }
        int leftNo = smallestNodeNo(root.left, no, k, smallestVal);
        no = 1 + leftNo;
        if (no == k) {
            smallestVal[0] = root.val;

            return no;
        }
        int rightNo = smallestNodeNo(root.right, no, k, smallestVal);

        return rightNo;
    }

    // Solution 2: use extra structure:
    // each node stores: # nodes under this node, o(logn)


}
