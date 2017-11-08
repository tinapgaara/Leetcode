package google.binarysearch;

/**
 * Created by yingtan on 11/5/17.
 *
 * 644. Maximum Average Subarray II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

 Example 1:
 Input: [1,12,-5,-6,50,3], k = 4
 Output: 12.75
 Explanation:
 when length is 5, maximum average value is 10.8,
 when length is 6, maximum average value is 9.16667.
 Thus return 12.75.
 */
public class MaxAverageSubarrayII {

    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        double low = Double.MIN_VALUE;
        double high = Double.MAX_VALUE;
        while(high - low > 0.000004) {
            double mid = low + (high - low) / 2;
            if (largerThanZero(mid, nums, k)) {
                low = mid;
            }
            else {
                high = mid;
            }
        }
        return high;
    }

    // find if this avg make sure there are k continuous subarray sum >= 0
    // //Check whether we can find a subarray whose average is bigger than x
    private boolean largerThanZero(double avg, int[] nums, int k) {
        double[] newnums = new double[nums.length];
        for (int i = 0 ; i < nums.length; i ++) {
            newnums[i] = nums[i] - avg;
        }
        double sum = 0;
        for (int i = 0 ; i < k ; i ++) {
            sum = sum + newnums[i];
        }
        if (sum > 0) return true;

        return false;
        //for ()
    }

}
