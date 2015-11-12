package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yingtan on 11/10/15.
 */
/*
*Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
* */
public class ClosetBSTTreeII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        List<Integer> res = new ArrayList<Integer>();
        if ((root == null) || (k == 0)) return res;

        Stack<Integer> precessors = new Stack<Integer>();
        Stack<Integer> successors = new Stack<Integer>();
        inorderIncrease(root, precessors, target, k);
        inorderDecrease(root, successors, target, k);

        System.out.println("pre" + precessors.size());
        System.out.println("suc" + successors.size());

        int i = 0;
        while (i < k) {
            if (precessors.isEmpty()) {
                res.add(successors.pop());
            }
            else if (successors.isEmpty()) {
                res.add(precessors.pop());
            }
            else {
                double diffPre = Math.abs((double)(precessors.peek() - target));
                double diffSuc = Math.abs((double)(successors.peek() - target));
                if (diffPre < diffSuc) {
                    res.add(precessors.pop());
                }
                else {
                    res.add(successors.pop());
                }
            }
            i ++;
        }
        return res;
    }

    public void inorderIncrease(TreeNode root, Stack<Integer> precessors, double target, int k) {

        if (root.left != null) {
            inorderIncrease(root.left, precessors, target, k);
        }
        if (root.val <= target) { // Pay attention !!! must be <= here
            precessors.push(root.val);
        }
        if (root.val > target){
            return;
        }

        if (root.right != null) {
            inorderIncrease(root.right, precessors, target, k);
        }
    }

    public void inorderDecrease(TreeNode root, Stack<Integer> successors, double target, int k) {

        if (root.right != null) {
            inorderDecrease(root.right, successors, target, k);
        }
        if (root.val > target) {  // Pay attention !!! must be > here
            successors.push(root.val);
        }
        if (root.val < target){
            return;
        }
        if (root.left != null) {
            inorderDecrease(root.left, successors, target, k);
        }
    }

    public static void main(String[] args) {
        ClosetBSTTreeII tree = new ClosetBSTTreeII();
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;

        TreeNode node4 = new TreeNode(1);
        node2.left = node4;

        tree.closestKValues(node1, 2.0, 3);

    }
}
