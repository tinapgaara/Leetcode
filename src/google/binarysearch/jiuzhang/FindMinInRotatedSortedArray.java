package google.binarysearch.jiuzhang;

/**
 * Created by yingtan on 10/29/17.
 *
 * 153. Find Minimum in Rotated Sorted Array
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 You may assume no duplicate exists in the array.
 */
public class FindMinInRotatedSortedArray {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        // find the first number which is smaller than last number in rotated sorted array
        int target = nums[nums.length - 1];
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid - 1;
            }
            else if (nums[mid] < target) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        if (nums[start] <= target) {
            return nums[start];
        }
        if (nums[end] <= target) {
            return nums[end];
        }
        return -1;
    }
}
