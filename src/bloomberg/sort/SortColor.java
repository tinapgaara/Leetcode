package bloomberg.sort;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* 0 in left side,  1 in middle and 2 in right side
*
*
* */
public class SortColor {

    public void sortColors(int[] nums) {

        if ((nums == null) || (nums.length == 0)) return;

        //Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;
        // move all 0 to left side

        // low = 0   high = 1/ 2:  low ++ high --  high = 0: low ++
        // low = 1   high = 0 : swap, low ++, high --   high = 1/2: high --
        // low = 2   high = 0: swap low ++ high --  high = 1/2: high --;
        while (low < high) {
            int lowVal = nums[low];
            int highVal = nums[high];
            if (lowVal == 0) {
                if (highVal != 0) {
                    low ++;
                    high --;
                }
                else {
                    low ++;
                }
            }
            else {
                if (highVal == 0) {
                    nums[low] = highVal;
                    nums[high] = lowVal;
                    low ++;
                    high --;
                }
                else {
                    high --;
                }
            }
        }

        //  move all 2 to right side
        low = 0;
        while (low < nums.length) {
            if (nums[low] == 0) low ++;
            else break;
        }

        high = nums.length - 1;
        // low = 1   high = 1: low++;  high = 2: low ++ high --
        // low = 2   high = 1: swap, low ++; high --;  high = 2: high --;

        // make sure all 0 is previous than 1
        while (low < high) {
            int lowVal = nums[low];
            int highVal = nums[high];
            if (lowVal == 1) {
                if (highVal == 1) {
                    low ++;
                }
                else {
                    low ++;
                    high --;
                }
            }
            else {
                if (highVal == 1) {
                    nums[low] = highVal;
                    nums[high] = lowVal;
                    low ++;
                    high --;
                }
                else {
                    high --;
                }
            }
        }

    }
}
