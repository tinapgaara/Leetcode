package bloomberg;

/**
 * Created by yingtan on 10/16/15.
 */
/*
* Leetcode: move zeros to right side
*
*
* */
public class MoveZeros {

    // Solution 2: use one pointer, move to right
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

    // only contains 0 and 1 : low high
    // 10000000000 : will need to swap so many times ? how to solve ?
    // use two pointer, one from left, one from right
    public void moveZero2Left(int[] nums) {

        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if ( (nums[low] == 0) && (nums[high] == 0) ) {
                low ++;
            }
            else if ((nums[low] == 0) && (nums[high] == 1)) {
                low ++;
                high --;
            }
            else if ((nums[low] == 1) && (nums[high] == 0)) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
            }
            else if ((nums[low] == 1) && (nums[high] == 1)) {
                high --;
            }
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

    public static void main(String[] args) {
        MoveZeros ob = new MoveZeros();
        int[] nums = new int[]{1,0,1,0,1,0,0,1};
        ob.moveZero2Left(nums);
        System.out.println("h");
    }
}
