package bloomberg;

/**
 * Created by yingtan on 10/16/15.
 */
/*
* Leetcode: move zeros
*
*
* */
public class MoveZeros {

    // Solution 2: use one pointer
    public void moveZeroes(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return;
        int nonZeroCount = 0;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] != 0) {
                nums[nonZeroCount] = nums[i];
                nonZeroCount ++;
            }
        }
        for (int i = nonZeroCount ; i < nums.length; i ++) {
            nums[i] = 0;
        }
    }

    // Solution 1: use two pointers + 1: three
    public void moveZeroes_2(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                int zeroIndex = i;
                int j = zeroIndex + 1;
                while ( ( zeroIndex < nums.length) && ( j  < nums.length ) ) {
                    if (nums[j] != 0) {
                        int tmp = nums[j];
                        nums[j] = nums[zeroIndex];
                        nums[zeroIndex] = tmp;

                        zeroIndex = j;
                        j = zeroIndex + 1;

                    } else {
                        j ++;
                    }
                }
            }
            i ++;
        }
    }


}
