package hashtable;

/**
 * Created by yingtan on 3/1/18.
 *
 * 697. Degree of an Array
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

 Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

 Example 1:
 Input: [1, 2, 2, 3, 1]
 Output: 2
 Explanation:
 The input array has a degree of 2 because both elements 1 and 2 appear twice.
 Of the subarrays that have the same degree:
 [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 The shortest length is 2. So return 2.
 Example 2:
 Input: [1,2,2,3,1,4,2]
 Output: 6
 */
import java.util.*;
public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, int[]> numToCountAndIndexs = new HashMap<>();
        for (int i = 0 ; i < nums.length; i ++) {
            if (numToCountAndIndexs.containsKey(nums[i])) {
                int[] countAndIndexs = numToCountAndIndexs.get(nums[i]);
                int count = countAndIndexs[0];
                int start = countAndIndexs[1];
                int end = countAndIndexs[2];
                numToCountAndIndexs.put(nums[i], new int[]{count + 1, start, i});
            }
            else {
                numToCountAndIndexs.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxCount = 0;
        int minLen = nums.length;
        for (Integer num : numToCountAndIndexs.keySet()) {
            int[] countAndIndexs = numToCountAndIndexs.get(num);
            int count = countAndIndexs[0];
            int start = countAndIndexs[1];
            int end = countAndIndexs[2];
            if (count > maxCount) {
                maxCount = count;
                minLen = end - start + 1;
            }
            else if (count == maxCount) {
                minLen = Math.min(minLen, end - start + 1);
            }
        }
        return minLen;
    }
}
