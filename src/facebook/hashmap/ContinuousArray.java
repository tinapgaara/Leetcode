package facebook.hashmap;

import java.util.HashMap;

/**
 * Created by yingtan on 5/28/17.
 */
public class ContinuousArray {
    /*
        The idea is to change 0 in the original array to -1.
        Thus, if we find SUM[i, j] == 0 then we know there are even number
        of -1 and 1 between index i and j. Also put the sum to index mapping
        to a HashMap to make search faster.
    */
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = 0;
        HashMap<Integer, Integer> sumToIndex = new HashMap<Integer, Integer>();
        sumToIndex.put(0, -1);
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] == 0) {
                // Important !!!!
                nums[i] = -1;
            }
        }
        int sum = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            sum = sum + nums[i];
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum) );
            }
            else
                sumToIndex.put(sum, i);
        }
        return max;
    }
}
