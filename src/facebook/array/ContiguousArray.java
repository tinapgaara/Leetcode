package facebook.array;

import java.util.*;

/**
 * Created by yingtan on 12/21/17.
 *
 * 525. Contiguous Array
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

 Example 1:
 Input: [0,1]
 Output: 2
 Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 Example 2:
 Input: [0,1,0]
 Output: 2
 Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        Map<Integer, Integer> zeroMinusOneToIndex = new HashMap<>();
        int zeroMinusOneCount = 0;
        zeroMinusOneToIndex.put(0, -1);
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] == 0) {
                zeroMinusOneCount ++;
            }
            else {
                zeroMinusOneCount --;
            }
            if (zeroMinusOneToIndex.containsKey(zeroMinusOneCount)) {
                int index = zeroMinusOneToIndex.get(zeroMinusOneCount);
                max = Math.max(i - index, max);
            }
            else {
                zeroMinusOneToIndex.put(zeroMinusOneCount, i);
            }
        }
        return max;
    }
}
