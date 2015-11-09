package google.array;

import java.util.Arrays;

/**
 * Created by yingtan on 11/8/15.
 */
public class MinNumNotInArr {

    public int findMin(int[] nums) {
        Arrays.sort(nums);
        int minNum = 1;
        for (int i = 0 ; i < nums.length; i ++) {
            if (nums[i] > minNum) {
                return minNum;
            }
            else {
                minNum ++;
            }
        }
        return minNum;
    }


    public static void main(String[] args) {
        MinNumNotInArr ob = new MinNumNotInArr();
        int[] nums = new int[]{1,2,3,4,8,9,10};
        System.out.println(ob.findMin(nums));
    }
}
