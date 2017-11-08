package google.binarysearch.jiuzhang;

/**
 * Created by yingtan on 10/29/17.
 *
 * For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.

 If the target number does not exist in the array, return -1.

 Have you met this question in a real interview? Yes
 Example
 If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.
 */
public class FirstPositionOfTarget {

    public int binarySearch(int[] nums, int target) {
        //write your code here
        if (nums == null || nums.length == 0) return 0;
        int start = 0;
        int end = nums.length - 1;

        // once (start, end) xiang lin, then, exist
        // try to narrow down the range
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            }
            else if (nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        // after narrowing down the range, do check again, and return result
        // range only contains two number: nums[start], nums[end]
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
