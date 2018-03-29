package linkedin.array;

/**
 * Created by yingtan on 11/18/17.
 *
 * 53. Maximum Subarray
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = nums[0];
        int max = nums[0];
        for (int i = 1 ; i < nums.length; i ++) {
            if (nums[i] > sum + nums[i]) {
                sum = nums[i];
            }
            else {
                sum = sum + nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
