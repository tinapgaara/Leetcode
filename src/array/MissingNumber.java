package array;

import java.util.Arrays;

/**
 * Created by yingtan on 9/28/15.
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        Arrays.sort(nums);
        int prev = nums[0];
        if (prev > 0) return 0;
        for (int i = 1 ; i < nums.length ; i ++) {
            if (nums[i] != prev + 1)
                return (prev + 1);
            prev = nums[i];
        }
        return nums.length;
    }
}
