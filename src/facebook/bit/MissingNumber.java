package facebook.bit;

/**
 * Created by yingtan on 3/19/18.
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        int xor = 0;
        for (; i < nums.length; i ++) {
            xor = xor ^ i ^ nums[i];
        }
        return xor ^ i;
    }
}
