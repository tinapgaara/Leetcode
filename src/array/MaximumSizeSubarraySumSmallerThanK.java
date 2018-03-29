package array;

/**
 * Created by yingtan on 1/23/18.
 */
public class MaximumSizeSubarraySumSmallerThanK {

    public int maxSizeSubarraySmallerThanK(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        int[] res = new int[2];
        sum[0] = 0;
        for (int i = 0 ; i < nums.length ; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[] minPrefixSumAfter = new int[nums.length];
        for (int i = nums.length - 1; i >= 0 ; i --) {
            if (i < nums.length - 1) {
                minPrefixSumAfter[i] = Math.min(minPrefixSumAfter[i + 1], sum[i + 1]);
            }
            else {
                minPrefixSumAfter[i] = sum[nums.length];
            }
        }
        int maxlen = 0;
        int start = 0;
        int end = 0;
        while(end < nums.length && start <= end) {
            int minPrefixSumAfterEnd = minPrefixSumAfter[end];
            // minsum[0, end] = sum[0, start - 1] = minsum[start, end]
            int minCurSum = minPrefixSumAfterEnd - sum[start];
            if (minCurSum < k) {
                // could be candidate
                if (end - start + 1 > maxlen) {
                    maxlen = end - start + 1;
                    res[0] = start; // record
                    res[1] = end;
                }
                //maxlen = Math.max(maxlen, end - start + 1);
                end ++;
            }
            else {
                start ++;
            }
        }
        System.out.println("start:" + res[0] + ", end:" + res[1]);
        return maxlen;
    }

    public static void main(String[] args) {
        int[] nums = {1, -4, 3, -2, 1, 5};
        MaximumSizeSubarraySumSmallerThanK ob = new MaximumSizeSubarraySumSmallerThanK();
        ob.maxSizeSubarraySmallerThanK(nums, 3);
    }
}
