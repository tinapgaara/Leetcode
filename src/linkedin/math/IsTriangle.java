package linkedin.math;

import java.util.Arrays;

/**
 * Created by yingtan on 11/28/17.
 *
 * 返回sorted数组里，是否存在三角形三边。（老提了） 2.

 */
public class IsTriangle {

    public boolean isTriangle(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length; i ++) {
            if (nums[i] > 0 && nums[i] + nums[i+1] < nums[i + 2]) {
                return true;
            }
        }
        return false;
    }
}
