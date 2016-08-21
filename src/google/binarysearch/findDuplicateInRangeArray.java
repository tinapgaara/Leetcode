package google.binarysearch;

/**
 * Created by yingtan on 12/14/15.
 */
/*
*
* Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
* */


public class findDuplicateInRangeArray {

    public int findDuplicate(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        return binarySearch(nums, 0, nums.length-1);
    }

    public int binarySearch(int[] nums, int low, int high) {
        if (low > high) {
            return low;
        }
        int med = (low + high) / 2;
        int countSmallerThanMed = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] <= med) {
                countSmallerThanMed ++;
            }
        }
        if (countSmallerThanMed > med) {
            return binarySearch(nums, low, med-1);
        }
        else {
            return binarySearch(nums, med+1, high);
        }
    }

    public static void main(String[] args) {
        findDuplicateInRangeArray ob = new findDuplicateInRangeArray();
        int[] nums = new int[]{1,1,2};
        ob.findDuplicate(nums);
    }
}
