package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 *
 * 137. Single Number II
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?


 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0 ; i < 32; i ++) {
                int bit = num & 1;
                count[i] = count[i] + bit;
                num = num >> 1;
            }
        }
        int res = 0;
        for (int i = 0 ; i < count.length; i ++) {
            res = res | ((count[i] % 3) << i);
        }
        return res;
    }
}
