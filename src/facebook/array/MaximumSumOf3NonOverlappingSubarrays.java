package facebook.array;

import java.util.Arrays;

/**
 * Created by yingtan on 2/11/18.
 *
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 */
public class MaximumSumOf3NonOverlappingSubarrays {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res = new int[3];
        if (nums == null) return res;
        int[] sum = new int[nums.length + 1];
        // scan the array from left to right, record the subarray's start index which has the max sum before or at the current index (I seen so far)
        int[] maxsumLeftstartIndex = new int[nums.length];
        // scan the array from right to left, record the subarray's start index which has the max sum at or after the current index. (I seen so far)
        int[] maxsumRightstartIndex = new int[nums.length];
        // sum[i+1] -> sum(0-i)
        for (int i = 0 ; i < nums.length; i ++) {
            sum[i+1] = sum[i] + nums[i];
        }
        int leftmax = -1;
        for (int i = 0 ; i <= nums.length - k; i ++) {
            // k subsum starting from ith index: [i - i + k -1]
            if (sum[i + k] - sum[i] > leftmax) {
                leftmax = sum[i + k] - sum[i];
                maxsumLeftstartIndex[i] = i;
            }
            else {
                maxsumLeftstartIndex[i] = maxsumLeftstartIndex[i-1];
            }
        }
        int rightmax = -1;
        for (int i = nums.length -k ; i >= 0 ; i --) {
            // k subsum starting from ith index: [i - i + k -1]
            if (sum[i + k] - sum[i] > rightmax) {
                rightmax = sum[i + k] - sum[i];
                maxsumRightstartIndex[i] = i;
            }
            else {
                maxsumRightstartIndex[i] = maxsumRightstartIndex[i+1];
            }
        }
        int max = 0;
        // test each middle start pos important !!! i = k ; i <= len - (2 *k - 1)
        for (int i = k ; i < nums.length - (2 * k - 1); i ++) {
            int middlesum = sum[i + k] - sum[i];
            int leftmaxSumStart = maxsumLeftstartIndex[i - k];
            int rightmaxsumStart = maxsumRightstartIndex[i + k];
            int leftMaxSum = sum[leftmaxSumStart + k] - sum[leftmaxSumStart];
            int rightMaxSum = sum[rightmaxsumStart + k] - sum[rightmaxsumStart];
            if (leftMaxSum + middlesum + rightMaxSum > max) {
                max = leftMaxSum + middlesum + rightMaxSum;
                res[0] = leftmaxSumStart;
                res[1] = i;
                res[2] = rightmaxsumStart;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        MaximumSumOf3NonOverlappingSubarrays ob = new MaximumSumOf3NonOverlappingSubarrays();
        int[] nums = {1,2,1,2,6,7,5,1};
        ob.maxSumOfThreeSubarrays(nums, 2);
    }
}
