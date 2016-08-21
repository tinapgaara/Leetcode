package bloomberg.array;

/**
 * Created by yingtan on 11/12/15.
 */
/*
* Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
* */
public class MaxSubarray {

    public int maxSubArray(int[] nums) {
        if ((nums == null) || (nums.length == 0)) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i = 0 ; i < nums.length ; i ++) {
            curSum = curSum + nums[i];
            if (curSum < nums[i]) {
                curSum = nums[i];
            }
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }

    public int maxSubArray(int[] nums, int low, int high) {
        if (low > high) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i = low ; i <= high ; i ++) {
            curSum = curSum + nums[i];
            if (curSum < nums[i]) {
                curSum = nums[i];
            }
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }

    public int maxTwoSubArrays(int[] nums) { // o(n^2)

        int max = Integer.MIN_VALUE;
        for (int split = 1; split < nums.length -2; split ++) {
            int left = maxSubArray(nums, 0, split);
            int right = maxSubArray(nums, split+1, nums.length-1);
            max = Math.max(max, left+right);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubarray ob = new MaxSubarray();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(ob.maxTwoSubArrays(nums));
    }
}
