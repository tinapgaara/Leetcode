package bloomberg.binarysearch;

/**
 * Created by yingtan on 10/26/15.
 */
/**
 * binary search
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */

public class SearchInRotatedSortedArray {

    // two cases:
    // 2 4 5 6 7 0 1   : when A[med] > A[low]
    // if (target >= A[low] && target < A[med]) search left; else search right
    // 6 7 0 1 2 4 5  : when A[med] < A[high]
    //  if (target > A[med] && target <= A[high]) search right; else search left

    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int low, int high) {

        if (low > high) return -1;

        int med = (low + high) / 2;
        if (nums[med] == target) {
            return med;
        }
        if (nums[med] < nums[high]) {
            if ((target > nums[med]) && (target <= nums[high])) {
                return binarySearch(nums, target, med+1,high);
            }
            else {
                return binarySearch(nums, target, low, med-1);
            }
        }
        else { // low  < med
            if ((target >= nums[low]) && (target < nums[med])) {
                return binarySearch(nums, target, low, med-1);
            }
            else {
                return binarySearch(nums, target, med+1, high);
            }
        }
    }
}
