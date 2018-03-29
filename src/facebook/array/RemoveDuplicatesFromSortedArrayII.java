package facebook.array;

/**
 * Created by yingtan on 2/10/18.
 *
 * 80. Remove Duplicates from Sorted Array II
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Follow up for "Remove Duplicates":
 What if duplicates are allowed at most twice?

 For example,
 Given sorted array nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int nextToFill = 2;
        for (int i = 2; i < nums.length; i ++) {
            if (nums[i] != nums[nextToFill - 2]) {
                nums[nextToFill] = nums[i];
                nextToFill ++;
            }
        }
        return nextToFill;
    }
}
