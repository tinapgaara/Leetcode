package facebook.twoPointer;

/**
 * Created by yingtan on 5/13/17.
 *
 * 283. Move Zeroes
 *
 * Total Accepted: 179284
 Total Submissions: 364902
 Difficulty: Easy
 Contributor: LeetCode
 Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.

 */
public class MoveZeros {

    // Solution 1: use one pointer, but scan more than once

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // make all non-zeros to front of array
        int nonZeroIndex = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nonZeroIndex ++;
            }
        }

        // make rest of array to be zero
        for (int i = nonZeroIndex; i < nums.length; i ++) {
            nums[i] = 0;
        }

    }

    // Solution 2: use two pointers(i, j), both start from index 0. scan once
    // nums[i] == 0 && nums[j] == 0 : j ++
    // nums[i] == 0 && nums[j] != 0 : swap nums[i] nums[j], i ++, j ++
       // make sure all non-zeros are moved to front of array
    // else : i ++, j ++;

    // make sure before jth, all zeros are in end of array
    public void moveZeroes_twoPointer(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int i = 0 ;
        int j = 1;
        while (j < nums.length) {
            int prev = nums[i];
            int cur = nums[j];

            if (prev == 0 && cur == 0) {
                j ++;
            }
            else if (prev == 0) {
                // switch prev and cur
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;

                i ++;
                j ++;
            }
            else {
                i ++;
                j ++;
            }
        }
    }
}
