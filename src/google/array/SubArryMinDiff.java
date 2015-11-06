package google.array;

import java.util.Arrays;

/**
 * Created by yingtan on 11/5/15.
 */
public class SubArryMinDiff {

    /*
    * 给⼀一个array和⼀一个size k~找到⼀一个size k subset，并且保证最
        ⼤大和最⼩小的数相差最⼩小~
        subset: could not be consecutive
    * */

    public int subArrayMinDiff(int[] nums, int k) {
        // o(nlogn) + o(n)k
        Arrays.sort(nums);
        int minDiff = nums[nums.length -1] - nums[0];
        for (int i = 0 ; i < nums.length -k ; i ++) {
            int j = i + k -1;
            minDiff = Math.min(minDiff, nums[j] - nums[i]);
        }
        return minDiff;
    }
}
