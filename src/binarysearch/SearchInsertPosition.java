package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 35. Search Insert Position
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Example 1:

 Input: [1,3,5,6], 5
 Output: 2
 Example 2:

 Input: [1,3,5,6], 2
 Output: 1
 Example 3:

 Input: [1,3,5,6], 7
 Output: 4
 Example 1:

 Input: [1,3,5,6], 0
 Output: 0

 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        // find the first pos which is larger than target
        if (nums == null || nums.length == 0) return -1;
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        if (nums[low] >= target) {
            return low;
        }
        else if (nums[high] >= target) {
            return high;
        }
        return nums.length;
    }
}
