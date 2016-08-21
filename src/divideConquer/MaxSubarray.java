package divideConquer;

/**
 * Created by yingtan on 9/20/15.
 */
public class MaxSubarray {

    // Solution 1: divide and conquer
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int len = nums.length;
        return recurMaxSubArr(nums, 0, len -1);
    }

    public int recurMaxSubArr(int[] nums, int low, int high) {
        int middle = (low + high) / 2;
        if (high == low) {
            return nums[low];
        }
        int leftMax = recurMaxSubArr(nums, low, middle);
        int rightMax = recurMaxSubArr(nums, middle + 1, high);

        int middleMax = findMaxSubArr(nums, low, middle, high);
        System.out.println("l:"+leftMax+", r:"+rightMax+", middle:"+middleMax);

        int tmpMax = Math.max(leftMax, rightMax);
        int finalMax = Math.max(tmpMax, middleMax);
        return finalMax;
    }

    public int findMaxSubArr(int[] nums, int low, int middle, int high) {
        int len = nums.length;
        if (middle < len) {
            int i = middle;
            int leftSum = 0, leftMaxSum = Integer.MIN_VALUE;
            while ( i >= low) {
                leftSum = leftSum + nums[i];
                leftMaxSum = Math.max(leftMaxSum, leftSum);
                i --;
            }
            int j = middle + 1;
            int rightSum = 0, rightMaxSum = Integer.MIN_VALUE;
            while (j <= high) {
                rightSum = rightSum + nums[j];
                rightMaxSum = Math.max(rightMaxSum, rightSum);
                j ++;
            }
            System.out.println("leftMaxSum:"+leftMaxSum + ", rightMaxSum:" + rightMaxSum);
            return leftMaxSum + rightMaxSum;
        }
        return 0;
    }

    // Solution 2: DP
    // We should ignore the previous twoPointers.sum if current element's value is larger than that twoPointers.sum
    // Also, keep tracking all possible maxium during each step.
    public int maxSubArray_DP(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) {
            return 0;
        }
        int len = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i = 0 ; i < len; i ++) {
            curSum = curSum + nums[i];
            if (nums[i] > curSum) {
                curSum = nums[i];
            }
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    // Solution 3: Kadane alg
    // Assume: at least one positive number in arr. so when twoPointers.sum < 0, reset to be zero
    public int maxSubArray_Kadane(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            curSum = curSum + nums[i];
            if (curSum < 0) {
                curSum = 0;
            }
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubarray ob = new MaxSubarray();
        int[] nums = new int[]{-1, -1, -2, -2};
        ob.maxSubArray(nums);

    }
}
