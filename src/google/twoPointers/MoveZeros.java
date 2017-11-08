package google.twoPointers;

/**
 * Created by yingtan on 11/2/17.
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int nonZero = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[nonZero] = nums[j];
                nonZero ++;
            }
            j ++;
        }
        for (int i = nonZero; i < nums.length; i ++) {
            nums[i] = 0;
        }
    }
}
