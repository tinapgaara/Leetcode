package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 34. Search for a Range
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 */
public class SearchForARange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) return res;
        int first = findFirstK(nums, target);
        int last = findLastK(nums, target);
        return new int[]{first, last};

    }
    // in sorted array
    public int findFirstK(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int med = low + (high - low) / 2;
            if (nums[med] < k) {
                low = med + 1;
            }
            else if (nums[med] > k) {
                high = med - 1;
            }
            else {
                high = med;
            }
        }
        if (nums[low] == k) {
            return low;
        }
        else if (nums[high] == k) {
            return high;
        }
        return -1;
    }
    // in sorted array
    public int findLastK(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int med = low + (high - low) / 2;
            if (nums[med] < k) {
                low = med + 1;
            }
            else if (nums[med] > k) {
                high = med - 1;
            }
            else {
                low = med;
            }
        }
        if (nums[high] == k) {
            return high;
        }
        else if (nums[low] == k) {
            return low;
        }
        return -1;
    }
}
