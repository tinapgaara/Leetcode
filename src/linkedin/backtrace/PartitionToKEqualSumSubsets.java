package linkedin.backtrace;

/**
 * Created by yingtan on 11/19/17.
 *
 *
 Add to List
 698. Partition to K Equal Sum Subsets
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.

 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            sum = sum + nums[i];
        }
        sum = sum / k;
        boolean[] vis = new boolean[nums.length];
        return canPartition(nums, k, vis, 0, 0, sum);
    }

    public boolean canPartition(int[] nums, int k, boolean[] vis, int index, int curSum, int sum) {
        // base case
        // important !!!
        if (k == 0) return true;
        if (curSum == sum) {
            return canPartition(nums, k-1, vis, 0, 0, sum);
        }
        // important !!! index starts with i instead of zero, or Time Exceed Limit (because subset 1,2,3 == 1,3,2)
        for (int i = index ; i < nums.length; i ++) {
            if (! vis[i]) {
                vis[i] = true;
                if (canPartition(nums, k, vis, i + 1, curSum + nums[i], sum)) {
                    return true;
                }
                vis[i] = false;
            }
        }
        return false;
    }
}
