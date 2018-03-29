package rangesum;

/**
 * Created by yingtan on 3/17/18.
 * Eg: 327. Count of Range Sum(maintain selfcount,
 * smallercount, largercount). Using sum as node’s val.
 * TreeSet的变形， tree.subset()这个方法 = totalcount - treeset.lower(lower) - treeset.higher(upper). But treeset can not store duplicate keys, in order to search the susbset’s size across duplciate keys, we maintain our own augmented BST tree. Using selfcount to store duplicate key’s count

 327. Count of Range Sum
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

 Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that.

 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class CountOfRangeSum {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        // how many leaf nodes are in this range
        int selfcount;
        long val;
        int smallercount;
        int largercount;

        public TreeNode(long val) {
            selfcount = 1;
            this.val = val;
        }
    }
    // given you range: sums[j]- upper, sums[j] -lower, calculate how many previous i are lie in this range
// -> classic treeset.subset(lower, higher), however, treeset can not deal with duplicate keys,
// -> so we create our own TreeNode, and use a variable: selfcount to keep duplicate count.
// range count = total - lowercount(lower) - highercount(higher)
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        long[] sums = new long[nums.length + 1];
        TreeNode root = new TreeNode(sums[0]);
        int count = 0;
        for (int j = 0; j < nums.length; j ++) {
            sums[j + 1] = sums[j] + nums[j];
            long lowval = sums[j + 1] - upper;
            long highval = sums[j + 1] - lower;
            count = count + rangeCount(root, lowval, highval);
            //System.out.println(count);
            insert(root, sums[j + 1]);
        }
        return count;
    }

    public TreeNode insert(TreeNode root, long sum) {
        if (root == null) {
            return new TreeNode(sum);
        }
        if (root.val == sum) {
            root.selfcount ++;
        }
        else if (root.val < sum) {
            root.largercount ++;
            root.right = insert(root.right, sum);
        }
        else {
            root.smallercount ++;
            root.left = insert(root.left, sum);
        }
        return root;
    }

    // logic is same as TreeSet.subset = TreeSet.totalSize - TreeSet.lower(lowval) - TreeSet.higher(highval)
    public int rangeCount(TreeNode root, long lowval, long highval) {
        if (root == null) return 0;
        return root.selfcount + root.largercount + root.smallercount - smallerCount(root,lowval) - largerCount(root, highval);
    }

    public int smallerCount(TreeNode root, long val) {
        if (root == null) return 0;
        // count how many nodes smaller than this val
        if (root.val == val) {
            return root.smallercount;
        }
        else if (root.val < val) {
            return root.smallercount + root.selfcount + smallerCount(root.right, val);
        }
        else {
            return smallerCount(root.left, val);
        }
    }
    public int largerCount(TreeNode root, long val) {
        if (root == null) return 0;
        // count how many nodes smaller than this val
        if (root.val == val) {
            return root.largercount;
        }
        else if (root.val > val) {
            return root.largercount + root.selfcount + largerCount(root.left, val);
        }
        else {
            return largerCount(root.right, val);
        }
    }

}
