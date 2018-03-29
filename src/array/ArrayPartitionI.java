package array;

import java.util.Arrays;

/**
 * Created by yingtan on 3/1/18.
 *
 * 561. Array Partition I
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

 Example 1:
 Input: [1,4,3,2]

 Output: 4
 Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 */
public class ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }
        Arrays.sort(nums);
        int i = 0 ;
        int sum = 0;
        while(i < nums.length) {
            sum = sum + nums[i];
            i = i + 2;
        }
        return sum;
    }
}
