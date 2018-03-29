package facebook.array;

/**
 * Created by yingtan on 2/10/18.
 *
 * 26. Remove Duplicates from Sorted Array
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example:

 Given nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int nextFill = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] != nums[nextFill - 1]) {
                nums[nextFill] = nums[i];
                nextFill ++;
            }
        }
        return nextFill;
    }
}
