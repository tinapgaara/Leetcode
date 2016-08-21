package array;

/**
 * Created by yingtan on 9/29/15.
 */
public class MoveZeros {

    // You must do this in-place without making a copy of the array
    public void moveZeroes(int[] nums) {
        if ( (nums == null) || (nums.length == 0) ) return;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                int k = i;
                int j = k + 1;
                while ( (j <  nums.length) && (k < nums.length)) {
                    if (nums[j] != 0) {
                        int tmp = nums[j];
                        nums[j] = nums[k];
                        nums[k] = tmp;
                        k = j;
                        j = k + 1;
                    }
                    else {
                        j ++;
                    }
                }
            }
            i ++;
        }
    }
}
