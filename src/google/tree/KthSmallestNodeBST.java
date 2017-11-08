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

    // More simple code than the later several versions
    public int kthSmallestSimplerSolution(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) return 0;

        while (root != null) {
            stack.push(root);
            root = root.left; // keep pushing left nodes
        }

        while (! stack.isEmpty()) { //  just judge if stack is empty
            TreeNode top = stack.pop(); // smallest node
            k --;
            if (k == 0) return top.val;

            TreeNode cur = top.right; // then keep pushing left nodes of this smallest node
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        return -1;
    }

    // Sol 2: recursion to do kth smallest
    public int kthSmallest_recur(TreeNode root, int k) {

        // Sol2 : using recur
        int[] ks = new int[1];
        ks[0] = k;
        return kthSmallestRecur(root, ks);
    }

    public int kthSmallestRecur(TreeNode root, int[] k) {
        if (root == null) return -1;
        int leftSmallest = kthSmallestRecur(root.left, k);
        if (k[0] == 0) return leftSmallest;
        // important !!!
        k[0] --;
        if (k[0] == 0) return root.val;

        return kthSmallestRecur(root.right, k);
    }




    // Solution 1.1: inorder traverse, using  stack, using loop
    // o(n)
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> nodes = new Stack<TreeNode>();

        TreeNode cur = root;
        while ((nodes != null) || (cur != null)) {
            // // keep pushing left, until cur == null
            if (cur != null) {
                nodes.push(cur);
                cur = cur.left;
            }
            else { // when cur == null, pop, point to right
                TreeNode top = nodes.pop();
                k --;
                if (k == 0) return top.val;
                cur = top.right;
            }
        }
        return 0;
    }

    // Solution 1.2: use recursive
    public int kthSmallest_recur_2(TreeNode root, int k) {

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
