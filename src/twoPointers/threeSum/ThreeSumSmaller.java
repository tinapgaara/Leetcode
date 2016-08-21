package twoPointers.threeSum;

import java.util.Arrays;

/**
 * Created by yingtan on 9/20/15.
 */
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {

        Arrays.sort(nums);
        int i = 0;
        int len = nums.length;

        int res = 0;
        while (i < len) {
            int max = target - nums[i];
            int j = i + 1;
            int k = len -1;
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum < max) {
                    int diff = k - j;
                    if (diff > 0) {
                        res = res + diff;
                    }
                    j++;
                }
                else {
                    k--;
                }
            }
            i++;
        }
        return res;
    }
}
