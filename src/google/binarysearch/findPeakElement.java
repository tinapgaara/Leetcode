package google.binarysearch;

/**
 * Created by yingtan on 11/10/15.
 */
/*
* A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
*
* */
public class findPeakElement {

    public int findPeakElement(int[] nums) {
        if ( (nums == null) || (nums.length == 0))  return 0;
        return recurFindPeakElement(nums, 0, nums.length -1);
    }

    public int recurFindPeakElement(int[] nums, int low, int high) {
        if (low > high) return Integer.MIN_VALUE; // really need ?

        int med = (low + high) / 2;

        if (((med-1) >= 0) && ((med + 1) < nums.length)) { // in between
            if ((nums[med] > nums[med-1]) && (nums[med] > nums[med+1])) {
                return med;
            }
            else {
                if (nums[med-1] > nums[med]) {
                    return recurFindPeakElement(nums, low, med-1); // search to higher value place
                }
                else {
                    return recurFindPeakElement(nums, med+1, high); // search to higher value place
                }
            }
        }
        else if ((med - 1) >= 0) { // last element
            if (nums[med] > nums[med-1]) {
                return med;
            }
            else {
                return recurFindPeakElement(nums, low, med-1);
            }
        }
        else if ((med + 1) < nums.length) { // first element
            if (nums[med] > nums[med + 1]) {
                return med;
            }
            else {
                return recurFindPeakElement(nums, med+1, high);
            }
        }

        return med;
    }
}
