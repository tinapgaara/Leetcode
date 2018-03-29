package array;

import java.util.Arrays;

/**
 * Created by yingtan on 12/18/17.
 * given an array, reorder entries so that even entries appear first, keep relative order of even and odd number
 */
public class MoveEvenEntries {

    // will not keep the order
    public void evenOdd(int[] nums) {
        // partition to  even + unclassified + odd
        int nextEven = 0;
        int nextOdd = nums.length - 1;
        while(nextEven < nextOdd) {
            if (nums[nextEven] % 2 == 0) {
                // this is even, so i is already classified
                nextEven ++;
            }
            else {
                // exchange with nextOdd one
                // nextOdd Odd
                // but nextOdd is unclassified one, so we can not increase i by 1
                int tmp = nums[nextOdd];
                nums[nextOdd] = nums[nextEven];
                nums[nextEven] = tmp;
                nextOdd --;
            }
        }
    }
    // keep relative order of even numbers
    //
    public void evenOdd_keeporder(int[] nums) {
        if (nums == null) return;
        // use pointer: nextEven = 0;
        // keep relative order of even number
        int nextEven = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] % 2 == 0) {
                // this is even
                int tmp = nums[nextEven];
                nums[nextEven] = nums[i];
                nums[i] = tmp;
                nextEven ++;
            }
        }
    }
    // how to keep relative order of even and odd numbers?

    public static void main(String[] args) {
        int[] nums = {4,7,1,3,2,6,5};
        MoveEvenEntries ob = new MoveEvenEntries();
        ob.evenOdd(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
