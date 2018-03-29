package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 *
 * 268. Missing Number
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1

 Input: [3,0,1]
 Output: 2
 Example 2

 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        int xor = 0;
        for (; i < nums.length; i ++) {
            xor = xor ^ i ^ nums[i];
        }
        return xor ^ i;
    }
}
