package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 162. Find Peak Element
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 A peak element is an element that is greater than its neighbors.

 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that num[-1] = num[n] = -∞.

 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 click to show spoilers.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.


 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        // pai chu fa: exclsuion
        // mid, if nums[mid] < nums[mid + 1] , not this
        if(nums == null || nums.length == 0) return -1;

        // find the first pos where curNum > nextNum
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            // still increasing, not the one we want, search right side
            if (mid +1 < nums.length && nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        if (nums[start] < nums[end]) {
            return end;
        }
        else if (nums[start] > nums[end]) {
            return start;
        }
        return start;
    }
}

