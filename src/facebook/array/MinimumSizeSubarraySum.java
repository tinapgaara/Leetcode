package facebook.array;

/**
 * Created by yingtan on 12/21/17.
 *
 * 209. Minimum Size Subarray Sum
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.

 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class MinimumSizeSubarraySum {
    // Sol 1: two pointer
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0;
        int end = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while(end < nums.length) {
            // extend window
            sum = sum + nums[end];
            while(sum >= s) { // important !! >= s, this is the given condition
                // smaller window
                min = Math.min(min, end - start + 1);
                sum = sum - nums[start];
                start ++;
            }
            end ++;
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }

    // Sol 2: binary search way
    public int minSubArrayLen_binarySearch(int s, int[] nums) {
        // [sum1, sum2, sum3, ... sumn]
        // for each sum1, find out sum1 + s and its corresponding leftmost end, then, store this to min value
        if (nums == null || nums.length == 0) return 0;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1 ; i < nums.length; i ++) {
            sums[i] = sums[i-1] + nums[i];
        }
        // then, sums array must be increasing (sorted array, find out the min index which is equals to given target)
        for (int i = 0; i < nums.length; i ++) {
            // consider: sum[0-n] >= s
            if (sums[i] >= s) {
                min = Math.min(min, i + 1);
            }
            int target = sums[i] + s;
            int minEnd = binarySearchLargerThan(sums, i, target);
            if (minEnd != -1) {
                min = Math.min(min, minEnd - i);
            }
        }
        if (min == Integer.MAX_VALUE) return 0;
        return min;
    }
    public int binarySearchLargerThan(int[] nums, int lowMin, int target) {
        int low = lowMin;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        if (nums[low] >= target) return low;
        else if (nums[high] >= target) return high;
        else return -1;
    }
}
