package binarysearch;

/**
 * Created by yingtan on 1/21/18.
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int xor = 0;
        for (int num : nums) {
            xor = xor ^ num;
        }
        return xor;
    }
}
