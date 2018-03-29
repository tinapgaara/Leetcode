package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 *
 * 260. Single Number III
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

 For example:

 Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

 Note:
 The order of the result is not important. So in the above example, [5, 3] is also correct.
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.


 */
public class SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        int xorOfTwo = 0;
        for (int num : nums) {
            xorOfTwo = xorOfTwo ^ num;
        }
        // since two are distinct, so there must be a bit is one: which means these two are diff
        // find out all elements which has one(least significant)in this bit, all these elements will be two same and add one more single element
        // after find out one single number, we find out another one.
        // such as 110  -> leastSignOneBit : 010
        int leastSignOneBit = xorOfTwo & (~(xorOfTwo - 1));
        // find out numbers which is same in this bit
        int xorOfOne = 0;
        for (int num : nums) {
            if ((num & leastSignOneBit) != 0) {

                // this num is 1 at that bit
                xorOfOne = xorOfOne ^ num;
            }
        }
        System.out.println(xorOfOne);
        int another = -1;
        for (int num : nums) {
            if (num == xorOfOne) {
                another = xorOfTwo ^ xorOfOne;
                res[0] = xorOfOne;
                res[1] = another;
                return res;
            }
        }
        return res;
    }
}
