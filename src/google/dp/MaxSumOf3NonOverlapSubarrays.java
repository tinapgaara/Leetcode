package google.dp;

/**
 * Created by yingtan on 11/4/17.
 *
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 */
public class MaxSumOf3NonOverlapSubarrays {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res = new int[3];
        if (nums == null || nums.length == 0) {
            return res;
        }
        int[] maxSumFromLeft = new int[nums.length];
        int[] maxSumFromRight = new int[nums.length];
        int[] sum = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i ++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int max = 0;
        for (int i = k - 1 ; i < nums.length; i ++) {
            int ksum = sum[i+1] - sum[i+1-k];
            if (ksum > max) {
                max = ksum;
                maxSumFromLeft[i] = i;
            }
            else {
                maxSumFromLeft[i] = maxSumFromLeft[i-1];
            }
        }
        max = 0;
        for (int i = nums.length - k; i >= 0; i --) {
            int ksum = sum[i+k] - sum[i];
            if (ksum > max) {
                max = ksum;
                maxSumFromRight[i] = i;
            }
            else {
                maxSumFromRight[i] = maxSumFromRight[i+1];
            }
        }
        max = 0;
        //   [0 ~ i-1] [i ~ i+k-1] [i+k ~ end]
        //  important !!!! using i <= len - 2*k
        for (int i = k; i <= nums.length - 2*k; i ++) {
            int middleSum = sum[i+k] - sum[i];
            int leftMaxSumEndIndex = maxSumFromLeft[i-1];
            int rightMaxSumStartIndex = maxSumFromRight[i+k];

            int leftMaxSum = sum[leftMaxSumEndIndex + 1] - sum[leftMaxSumEndIndex - k + 1];
            int rightMaxSum = sum[rightMaxSumStartIndex + k] - sum[rightMaxSumStartIndex];
            if (leftMaxSum + middleSum + rightMaxSum > max) {
                max = leftMaxSum + middleSum + rightMaxSum;
                res[0] = leftMaxSumEndIndex - k + 1;
                res[1] = i;
                res[2] = rightMaxSumStartIndex;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSumOf3NonOverlapSubarrays ob = new MaxSumOf3NonOverlapSubarrays();
        int[] nums = new int[]{7,13,20,19,19,2,10,1,1,19};
        ob.maxSumOfThreeSubarrays(nums, 3);
    }


}
