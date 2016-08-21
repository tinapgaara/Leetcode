package google.tree;

import tree.TreeNode;

/**
 * Created by yingtan on 11/5/15.
 */
public class SortedArr2BST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return null;
        int len = nums.length;
        return recurSortedBST(nums, 0, len-1);
    }

    public TreeNode recurSortedBST(int[] nums, int low, int high) {
        if (low > high) return null;
        int med = (low + high) / 2;

        TreeNode cur = new TreeNode(nums[med]);
        TreeNode left = recurSortedBST(nums, low, med-1);
        TreeNode right = recurSortedBST(nums, med+1, high);

        cur.left = left;
        cur.right = right;

        return cur;
    }
}
