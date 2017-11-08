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
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // assume no duplicate
            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                }
                else {
                    start = mid + 1;
                }
            }
            else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                }
                else {
                    end = mid - 1;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
