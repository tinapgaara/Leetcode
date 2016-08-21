package google.sum;

import java.util.Arrays;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* Question: An Array of integers is given,
* both +ve and -ve. You need to find the two elements such that their sum is closest to zero.
*
* */
public class TwoSumCloset {

    public int[] closetSum(int[] nums) {

        Arrays.sort(nums);

        int low = 0;
        int high = nums.length - 1;
        int minsum = Integer.MAX_VALUE;
        int minIndexLow = 0;
        int minIndexHigh = 0;

        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum < minsum) {
                minIndexHigh = high;
                minIndexLow = low;
            }

            if (sum > 0) {
                high --;
            }
            else
                low ++;
        }
        int[] res = new int[]{minIndexLow, minIndexHigh};
        return res;
    }
}
