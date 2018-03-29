package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 154. Find Minimum in Rotated Sorted Array II
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Follow up for "Find Minimum in Rotated Sorted Array":
 What if duplicates are allowed?

 Would this affect the run-time complexity? How and why?
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 The array may contain duplicates.


 */
public class FindMinRotatedSortedArrayII {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low ) /2;
            if (nums[mid] > nums[high]) {
                // min must exist after mid
                low = mid + 1;
            }
            else if (nums[mid] < nums[high]){
                // min must exist before/include mid
                high = mid;
            }
            else {
                // don't know which dir to go
                high --;
            }
        }
        if (nums[low] < nums[high]) {
            return nums[low];
        }
        else {
            return nums[high];
        }
    }
}
