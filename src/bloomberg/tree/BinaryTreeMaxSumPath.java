package bloomberg.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node
to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
* */
public class BinaryTreeMaxSumPath {

    public int maxPathSum(TreeNode root) {

        int[] res = recurMaxPathSum(root);
        return res[1];
    }

    public int[] recurMaxPathSum(TreeNode root) {
        int[] left = null;
        if (root.left == null) {
            if (root.val < 0) {
                left = new int[]{root.val, root.val};
            }
            else
                left = new int[]{0,0};
        }
        else {
            left = recurMaxPathSum(root.left);
        }
        int leftPathSumNoAccrossRoot = left[0];
        int leftMaxPathSum = left[1];

        int[] right = null;
        if (root.right == null) {
            if (root.val < 0)
                right = new int[]{root.val, root.val};
            else
                right = new int[]{0, 0};
        }
        else {
            right = recurMaxPathSum(root.right);
        }
        int rightPathSumNoAccrossRoot = right[0];
        int rightMaxPathSum = right[1];

        int[] res = new int[2];
        int noAccrossMax = Math.max(leftPathSumNoAccrossRoot + root.val, rightPathSumNoAccrossRoot + root.val);
        noAccrossMax = Math.max(root.val, noAccrossMax);
        res[0] = noAccrossMax;

        int accrossMax = Math.max(leftMaxPathSum, rightMaxPathSum);
        accrossMax = Math.max(accrossMax, leftPathSumNoAccrossRoot + root.val + rightPathSumNoAccrossRoot);
        accrossMax = Math.max(accrossMax, noAccrossMax);

        res[1] = accrossMax;

        return res;
    }

    public static void main(String[] args) {
        BinaryTreeMaxSumPath ob = new BinaryTreeMaxSumPath();

        // boundary case: -3
        TreeNode n1 = new TreeNode(-2);

        TreeNode n2 = new TreeNode(-1);
        n1.left = n2;
        /*
        TreeNode n3 = new TreeNode(4);
        n1.right = n3;

        TreeNode n4 = new TreeNode(4);
        n2.left = n4;
        */
        /*
        TreeNode n5 = new TreeNode(5);
        n2.right = n5;
        */

        System.out.println(ob.maxPathSum(n1));
    }
}
