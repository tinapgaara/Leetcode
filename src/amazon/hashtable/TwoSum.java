package amazon.hashtable;

/**
 * Created by yingtan on 3/22/18.
 */
import java.util.*;
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) return res;
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0 ; i < nums.length; i ++) {
            int anotherNum = target - nums[i];
            if (numToIndex.containsKey(anotherNum)) {
                int prevIndex = numToIndex.get(anotherNum);
                // don't use the same element twice
                res[0] = prevIndex;
                res[1] = i;
                return res;
            }
            numToIndex.put(nums[i], i);
        }
        return res;
    }
}
