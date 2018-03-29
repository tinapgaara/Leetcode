package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 *
 * 287. Find the Duplicate Number
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.

 */
public class FindTheDuplicateNumber {

    public int findDuplicate_binarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            int midSuppposedNum = mid + 1;
            int supposedSmallerCount = mid;
            // find out how many numbers in array < midSuppposedNum
            int smallerCount = 0;
            for (int num : nums) {
                if (num < midSuppposedNum) {
                    smallerCount ++;
                }
            }
            if (smallerCount > supposedSmallerCount) {
                // duplicate is on left side
                high = mid -1;
            }
            else {
                low = mid;
            }
        }
        int supposedLowNum = low + 1;
        int supposedHighNum = high + 1;
        int dupLow = 0;
        int dupHigh = 0;
        for (int num : nums) {
            if (num == supposedLowNum) {
                dupLow ++;
            }
            if (num == supposedHighNum) {
                dupHigh ++;
            }
        }
        if (dupLow > 1) {
            return supposedLowNum;
        }
        else if (dupHigh > 1) {
            return supposedHighNum;
        }
        return -1;
    }
}
