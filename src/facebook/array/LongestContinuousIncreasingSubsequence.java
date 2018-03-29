package facebook.array;

/**
 * Created by yingtan on 2/19/18.
 * 674. Longest Continuous Increasing Subsequence
 Input: [1,3,5,4,7]
 Output: 3
 Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.

 */
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1;
        int end = 1;
        int start = 0;
        while(end < nums.length) {
            if (nums[end] > nums[end - 1]) { // continuous increasing
                end ++;
            }
            else {
                max = Math.max(max, end - start);
                start = end;
                end ++;
            }
        }
        max = Math.max(max, end - start);
        return max;
    }
}
