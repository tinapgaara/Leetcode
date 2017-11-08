package google.tree;

/**
 * Created by yingtan on 8/19/17.
 *
 * 327. Count of Range Sum
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ? j), inclusive.

 Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that.

 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.


 */
public class CountOfRangeSum {

    public class TreeNode {
        // because this val is aggregated sums[0-j], need to use long
        long val;
        int sameValCount;
        int leftSmallerCount;
        int rightLargerCount;

        TreeNode left;
        TreeNode right;

        public TreeNode(long val) {
            this.val = val;
            sameValCount = 1;
            leftSmallerCount = 0;
            rightLargerCount = 0;

            left = null;
            right = null;
        }
    }

    public TreeNode insert(TreeNode root, long val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val == val) {
            root.sameValCount ++;
        }
        else if (val > root.val) {
            root.rightLargerCount ++;
            root.right = insert(root.right, val);
        }
        else {
            root.leftSmallerCount ++;
            root.left = insert(root.left, val);
        }
        return root;
    }

    public int inRangePossibility(TreeNode root, long lower, long upper) {
        int totalNodes = root.sameValCount + root.leftSmallerCount + root.rightLargerCount;
        int smaller = countSmaller(root, lower);
        int larger = countLarger(root, upper);
        return totalNodes - smaller - larger;
    }

    public int countSmaller(TreeNode root, long lower) {
        if (root == null) return 0;
        if (root.val == lower) {
            return root.leftSmallerCount;
        }
        if (root.val > lower) {
            return countSmaller(root.left, lower);
        }
        else {
            return root.leftSmallerCount + root.sameValCount + countSmaller(root.right, lower);
        }
    }

    public int countLarger(TreeNode root, long upper) {
        if (root == null) return 0;
        if (root.val == upper) {
            return root.rightLargerCount;
        }
        if (root.val < upper) {
            return countLarger(root.right, upper);
        }
        else {
            return countLarger(root.left, upper) + root.sameValCount + root.rightLargerCount;
        }
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums.length == 0) {
            return 0;
        }
        // [0, -2, 3, 2]
        long[] sums = new long[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }

        TreeNode root = new TreeNode(sums[0]);
        int res = 0;
        for (int j = 1; j < sums.length; j ++) {
            res = res + inRangePossibility(root, sums[j] - upper, sums[j] - lower);
            insert(root, sums[j]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 5, -1};
        CountOfRangeSum ob = new CountOfRangeSum();
        ob.countRangeSum(nums, -2, 2);
    }
}
