package array;

/**
 * Created by yingtan on 1/22/18.
 *
 * 300. Longest Increasing Subsequence
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] increasingSeq = new int[nums.length]; // dp_i stores the max length ending with num_i
        increasingSeq[0] = nums[0];
        int maxlen = 1;
        for (int i = 1 ; i < nums.length; i ++) {
            int num = nums[i];
            if (num > increasingSeq[maxlen - 1]) {
                maxlen ++;
                increasingSeq[maxlen - 1] = num;
            }
            else {
                int pos = findFirstLargerThan(increasingSeq, 0, maxlen -1, num);
                increasingSeq[pos] = num;
            }
        }
        return maxlen;
    }
    public int findFirstLargerThan(int[] num, int low, int high, int target) {
        while(low + 1 < high) {
            int mid = low + (high - low) /2;
            if (num[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        if (num[low] >= target) {
            return low;
        }
        else if (num[high] >= target) {
            return high;
        }
        return -1;
    }
}
