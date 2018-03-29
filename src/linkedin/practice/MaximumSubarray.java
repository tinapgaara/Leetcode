package linkedin.practice;

/**
 * Created by yingtan on 11/22/17.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        int sum = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (sum + nums[i] < nums[i]) {
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
