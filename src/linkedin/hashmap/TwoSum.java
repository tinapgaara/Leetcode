package linkedin.hashmap;

import java.util.HashMap;

/**
 * Created by yingtan on 11/28/17.
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null) return res;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < nums.length; i ++) {
            map.put(nums[i], i);
        }
        for (int i = 0 ; i < nums.length; i ++) {
            int num1 = nums[i];
            int num2 = target - nums[i];
            if (map.containsKey(num2)) {
                int index2 = map.get(num2);
                if (i != index2) {
                    res[0] = i;
                    res[1] = index2;
                    return res;
                }
            }
        }
        return res;
    }
}
