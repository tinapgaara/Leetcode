package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 *
 * 540. Single Element in a Sorted Array
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

 Example 1:
 Input: [1,1,2,3,3,4,4,8,8]
 Output: 2
 Example 2:
 Input: [3,3,7,7,10,11,11]
 Output: 10
 */
public class SingleElementInSortedArray {

    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (mid % 2 == 0) {
                if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                    // search right
                    low = mid + 1;
                }
                else if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    // search left
                    high = mid - 1;
                }
                else {
                    return nums[mid];
                }
            }
            else {
                if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                    // search left
                    high = mid - 1;
                }
                else if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    // search right
                    low = mid + 1;
                }
                else {
                    return nums[mid];
                }
            }
        }
        if (low == high) {
            return nums[low];
        }
        //System.out.println(low + "," + high);
        if (low > 0 && nums[low] == nums[low - 1]) {
            return nums[high];
        }
        else if (high < nums.length -1 && nums[high] == nums[high + 1]) {
            return nums[low];
        }
        return -1;
    }
}
