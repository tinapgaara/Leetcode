package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 33. Search in Rotated Sorted Array
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.


 */
public class SearchInRotatedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0;
        int high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) {
                // [mid, high] is mono increasing
                if (target >= nums[mid] && target <= nums[high]) {
                    // search in [mid, high]
                    low = mid;
                } else {
                    // search in [low, mid)
                    high = mid - 1;
                }
            }
            else {
                // [low, mid] is mono increasing
                if (target >= nums[low] && target <= nums[mid]) {
                    // search in [low, mid]
                    high = mid;
                } else {
                    // search in (mid, high]
                    low = mid + 1;
                }
            }
        }
        if (nums[low] == target) {
            return low;
        }
        else if (nums[high] == target) {
            return high;
        }
        return -1;
    }
}
