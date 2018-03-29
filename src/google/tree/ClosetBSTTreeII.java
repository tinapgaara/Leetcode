package google.tree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
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

    // k(logn)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> smaller = new Stack<TreeNode>();
        Stack<TreeNode> larger = new Stack<TreeNode>();
        List<Integer> res = new ArrayList<Integer>();
        while(root != null) {
            if (root.val < target) {
                smaller.push(root);
                root = root.right;
            }
            else {
                larger.push(root);
                root = root.left;
            }
        }

        for (int i = 0; i < k; i ++) {
            // when i = 0 , must be the closet one
            if (larger.isEmpty() || (! smaller.isEmpty() && (Math.abs(smaller.peek().val - target) < Math.abs(larger.peek().val - target)))) {
                TreeNode cur = smaller.peek();
                res.add(cur.val);
                updateSmaller(smaller, cur);
            }
            else {
                TreeNode cur = larger.peek();
                res.add(cur.val);
                updateLarger(larger, cur);
            }

        }
        return res;
    }

    public void updateSmaller(Stack<TreeNode> smaller, TreeNode cur) {
        // find smaller ones
        smaller.pop();
        if(cur.left != null) {
            smaller.push(cur.left);
            cur = cur.left;
            while(cur.right != null) {
                // Important !!!!
                smaller.push(cur.right);
                cur = cur.right;
            }
        }
    }

    public void updateLarger(Stack<TreeNode> larger, TreeNode cur) {
        // find larger ones
        larger.pop();
        if(cur.right != null) {
            larger.push(cur.right);
            cur = cur.right;
            while(cur.left != null) {
                // Important !!!!
                larger.push(cur.left);
                cur = cur.left;
            }
        }
    }
    // o(nlogn): inorder traversal
    /*
    还有一种解法是直接在中序遍历的过程中完成比较，当遍历到一个节点时，如果此时结果数组不到k个，我们直接将此节点值加入res中，如果该节点值和目标值的差值的绝对值小于res的首元素和目标值差值的绝对值，说明当前值更靠近目标值，则将首元素删除，末尾加上当前节点值，反之的话说明当前值比res中所有的值都更偏离目标值，由于中序遍历的特性，之后的值会更加的遍历，所以此时直接返回最终结果即可
    在来看一种利用最大堆来解题的方法，堆里保存的一个差值diff和节点值的pair，我们中序遍历二叉树(也可以用其他遍历方法)，然后对于每个节点值都计算一下和目标值之差的绝对值，由于最大堆的性质，diff大的自动拍到最前面，我们维护k个pair，如果超过了k个，就把堆前面大的pair删掉，最后留下的k个pair，我们将pair中的节点值取出存入res中返回即可

    */
    public void inOrderRecur(TreeNode root, double target, int k, LinkedList<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderRecur(root.left, target, k, list);
        if (list.size() == k) {
            if (Math.abs(root.val - target) < Math.abs(list.getFirst() - target)) {
                list.removeFirst();
                list.addLast(root.val);
            }
        }
        else {
            list.addLast(root.val);
        }
        inOrderRecur(root.right, target, k, list);
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
