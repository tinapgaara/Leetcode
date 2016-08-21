package google.sort;

/**
 * Created by yingtan on 11/16/15.
 */
/*
* Given an unsorted array, find the maximum difference between the successive elements
* in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the
32-bit signed integer range.
* */
public class RadixSort {

    public int[] radixSort(int[] nums) {

        for (int i = 0 ; i < 32; i ++) {
            nums = countSort(nums, i, 1);
        }
        return nums;
    }

    public int[] countSort(int[] nums, int digitIndex, int maxNum) {

        int[] counts = new int[maxNum + 1];
        for (int i = 0 ; i < nums.length ; i ++) {
            int digit = (nums[i] >> digitIndex) & 1;
            counts[digit]++;
        }
        for (int i = 1; i < counts.length ; i ++) {
            counts[i] = counts[i] + counts[i-1];
        }
        int[] res = new int[nums.length];
        for (int i = nums.length; i >= 0; i --) {
            int digit = (nums[i] >> digitIndex) & 1;
            int putIndex = counts[digit]-1;
            res[putIndex] = nums[i];

            counts[digit]--;
        }
        return res;
    }
}
