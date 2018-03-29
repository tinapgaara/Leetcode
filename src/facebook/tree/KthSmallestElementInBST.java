package facebook.tree;

/**
 * Created by yingtan on 3/17/18.
 *
 * 230. Kth Smallest Element in a BST
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
import tree.TreeNode;

import java.util.*;
public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        int[] ks = new int[1];
        ks[0] = k;
        if (root == null)  return -1;
        int[] res = new int[1];
        recurK(root, ks, res);
        return res[0];
    }
    public void recurK(TreeNode cur, int[] ks, int[] res) {
        if (cur == null) return;
        recurK(cur.left, ks, res);
        if (ks[0] == 0) {
            return;
        }
        ks[0] -- ;
        if (ks[0] == 0) {
            res[0] = cur.val;
            return;
        }
        recurK(cur.right, ks, res);
    }
    // Binary Search : this is still o(h * n)
    public int kthSmallest_binarySearch(TreeNode root, int k) {
        if (root == null) return -1;

        int leftCount = count(root.left);
        if (k <= leftCount) {
            return kthSmallest_binarySearch(root.left, k);
        }
        else {
            if (k == leftCount + 1) {
                return root.val;
            }
            return kthSmallest_binarySearch(root.right, k - leftCount - 1);
        }
    }
    //o(n)
    public int count(TreeNode cur) {
        if (cur == null) return 0;
        return count(cur.left) + count(cur.right) + 1;
    }


}
