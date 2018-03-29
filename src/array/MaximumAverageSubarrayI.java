package array;

/**
 * Created by yingtan on 3/1/18.
 *
 * 643. Maximum Average Subarray I
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

 Example 1:
 Input: [1,12,-5,-6,50,3], k = 4
 Output: 12.75
 Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 */
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int sum = 0;
        double max = Integer.MIN_VALUE;
        // moving window, two pointers
        for (int i = 0 ; i < nums.length; i ++) {
            sum = sum + nums[i];
            if (i - start + 1 == k) {
                max =  Math.max(max, ((double)sum) / k);
                sum = sum - nums[start];
                start ++;
            }
        }
        return max;
    }
}
