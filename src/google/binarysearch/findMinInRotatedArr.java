package google.binarysearch;

/**
 * Created by yingtan on 11/3/15.
 */
/*
* Find Minimum in Rotated Sorted Array
*
* Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

public class findMinInRotatedArr {

    public int findMin(int[] nums) {
        if ((nums == null) || (nums.length == 0))
            return 0;

        return recurFindMin(nums, 0, nums.length -1);
    }

    public int recurFindMin(int[] nums, int low, int high) {
        if (low > high) return -1;
        if (low == high) {
            return nums[low];
        }

        int med = (low + high) / 2;
        if (nums[med] < nums[high]) {
            return recurFindMin(nums, low, med);
        }
        else {
            return recurFindMin(nums, med+1, high);
        }
    }
/*  *
    * Find Minimum in Rotated Sorted Array II
    *
    * Follow up for "Find Minimum in Rotated Sorted Array":

        What if duplicates are allowed?

        Would this affect the run-time complexity? How and why?
    * */

    public int findMinDup(int[] nums) {
        if ((nums == null) || (nums.length == 0))
            return 0;

        int med = nums.length / 2;
        return Math.min(recurFindMinDup(nums, 0, med), recurFindMinDup(nums, med + 1, nums.length));
    }

    public int recurFindMinDup(int[] nums, int low, int high) {
        if (low >= high) {
            if (low -1 == nums.length -1) return nums[nums.length -1]; // Important !!!  for [0 1 2 3]
            else if (high + 1 == 0) return nums[0]; // Important !!! for [1 2 3 0]
            return nums[low];
        }

        int mid = (low + high)/2;
        return Math.min(recurFindMinDup(nums, low, mid),recurFindMinDup(nums, mid + 1, high)); // search on both side (left && right)
    }

    public static void main(String[] args) {
        findMinInRotatedArr ob = new findMinInRotatedArr();
        int[] nums= new int[]{4,1,3};
        System.out.println(ob.findMinDup(nums));
    }

}
