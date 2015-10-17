package bloomberg;

import java.util.Arrays;

/**
 * Created by max2 on 10/16/15.
 */
/*
* Byte = 8 bits
* Integer = 4 Byte = 32 bits
*
* two array,may have duplicates, find a pair which sums to target.
*
* */
public class TwoSumByte {

    public boolean isTwoSum(byte[] a1, byte[] a2, int target) {
        Arrays.sort(a1);
        Arrays.sort(a2);

        int low = 0;
        int high = a2.length-1;

        while ((low < a1.length) && (high >= 0)) {
            if (a1[low] + a2[high] < target) {
                low ++;
            } else if (a1[low] + a2[high] < target){
                high --;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        byte[] a1 = new byte[]{2,3,4,5,7,8};
        byte[] a2 = new byte[]{0,1,2,3,4};

        TwoSumByte ob = new TwoSumByte();
        System.out.println(ob.isTwoSum(a1, a2, 8));
    }
}
